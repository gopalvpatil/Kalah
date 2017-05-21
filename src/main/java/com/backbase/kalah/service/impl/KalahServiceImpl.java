package com.backbase.kalah.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backbase.kalah.configuration.KalahGameRules;
import com.backbase.kalah.dto.KalahBoard;
import com.backbase.kalah.dto.KalahGame;
import com.backbase.kalah.dto.KalahPlayer;
import com.backbase.kalah.exception.KalahException;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.util.KalahConstants;

/**
* <h1>KalahServiceImpl!</h1>
* This service is responsible for initiating the kalah game
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

@Service("kalahService")
public class KalahServiceImpl implements KalahService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KalahServiceImpl.class);
    
    @Autowired
    KalahGameRules kalahGameRules;

    /**
    * This method is called to initiate the game.
    * @param stonesPerPit
    * @return KalahGame : Returns the game's board with current player id.
    * @exception KalahException On input error.
    * @see KalahException
    */
    public KalahGame init(int stonesPerPit) throws KalahException {
    	LOGGER.debug("KalahServiceImpl:: init(): Initialise kalah Game");
    	
		int[] pits = new int[KalahConstants.TOTAL_NUM_OF_PITS];  
		Arrays.fill(pits, stonesPerPit);
		pits[KalahConstants.HOUSE_INDEX_PLAYER1] = 0;
		pits[KalahConstants.HOUSE_INDEX_PLAYER2] = 0;
		
		KalahBoard kalahBoard = new KalahBoard();
		kalahBoard.setPits(pits);
		
		KalahPlayer kalahPlayer = new KalahPlayer();
		kalahPlayer.setId(1);
    	
        return this.setKalahGameResponse(kalahPlayer, kalahBoard, false, "");
    }
    
    /**
    * This method is called to validate player's move.
    * @param pits, selectedPitsIndex, currentPlayerId
    * @return KalahGame : Returns the game's board with current player id.
    * @exception KalahException On input error.
    * @see KalahException
    */
    @Override
    public KalahGame validateMove(KalahGame kalahGameRequest) throws KalahException {
    	LOGGER.debug("KalahServiceImpl:: validateMove(): validate Move");
    	
    	int pits[] = kalahGameRequest.getKalahBoard().getPits();
    	int selectedPitsIndex = kalahGameRequest.getSelectedPitsIndex();
    	int currentPlayerId = kalahGameRequest.getKalahPlayer().getId();
    	String errorMessage = "";
    	boolean isError = false;

		if(selectedPitsIndex < 0 || selectedPitsIndex > pits.length) {
			errorMessage = " IllegalMoveError!!, Index does not exist.";
			isError = true;
		} else if(selectedPitsIndex == KalahConstants.HOUSE_INDEX_PLAYER1 || selectedPitsIndex == KalahConstants.HOUSE_INDEX_PLAYER2) {
			errorMessage = " IllegalMoveError!!, KalahPlayer must not sow seeds from the store";
			isError = true;
		}
		else if((currentPlayerId == 1 && selectedPitsIndex > KalahConstants.HOUSE_INDEX_PLAYER1) ||
				(currentPlayerId == 2 && selectedPitsIndex < KalahConstants.HOUSE_INDEX_PLAYER1)) {
			errorMessage = " IllegalMoveError!!, Current player must not sow from the opponent's pits";
			isError = true;
		}
		else if(pits[selectedPitsIndex] == 0) {
			errorMessage = " IllegalMoveError!!, Pit is empty.";
			isError = true;
		} else if(kalahGameRules.isGameOver(pits)) {
			errorMessage = " IllegalMoveError!!, Game is over";
			isError = true;
		} else {
			errorMessage = "";
			isError = false;
		}
        return this.setKalahGameResponse(kalahGameRequest.getKalahPlayer(), kalahGameRequest.getKalahBoard(), isError, errorMessage);
    }
    
    /**
    * This method is responsible for implementing a player's next move.
    * @param kalahPlayer, kalahBoard, selectedPitsIndex(index of the pit from which the player has chosen to move his stones), currentPlayerId 
    * @return KalahGame : Returns the game's board with current player id.
    * @exception KalahException On input error.
    * @see KalahException
    */
    @Override
    public KalahGame moveStones(KalahGame kalahGameRequest) throws KalahException {
    	LOGGER.debug("KalahServiceImpl:: moveStones(): player's next move");
    	
    	int pits[] = kalahGameRequest.getKalahBoard().getPits();
    	int selectedPitsIndex = kalahGameRequest.getSelectedPitsIndex();
    	int currentPlayerId = kalahGameRequest.getKalahPlayer().getId();
    	int nextPlayerId;
    	
    	//Get count for stones available in selected pit
    	int stones = pits[selectedPitsIndex];
        int lastPitsIndex = 0;
        
        //Empty player's selected pit
         pits[selectedPitsIndex] = 0;
        
        for (int i = 1; i <= stones ; i++) {
        	lastPitsIndex = (selectedPitsIndex + i) %  pits.length;
        	if((currentPlayerId == 1 && lastPitsIndex == KalahConstants.HOUSE_INDEX_PLAYER2) ||
    				(currentPlayerId == 2 && lastPitsIndex == KalahConstants.HOUSE_INDEX_PLAYER1)) {
        		//increase missed value
        		stones++; 
        		//Skip opponent's house.
        		continue;
        	}
        	 pits[lastPitsIndex]++;
        }
       
        //If the players last stone lands in his own Kalah, he gets another turn
        //Check if the player wins an extra round
        boolean isOwnHouse = kalahGameRules.isLastStoneLandsInOwnKalah(lastPitsIndex, currentPlayerId);
        if(isOwnHouse) {
        	//play again player wins extra round
        	nextPlayerId = currentPlayerId;
        } else { 
            boolean captureFlag = kalahGameRules.isLastStoneLandsOwnEmptyPit(pits, lastPitsIndex, currentPlayerId);
            if(captureFlag) {
            	pits = this.captureStones(pits, currentPlayerId, lastPitsIndex);
            }
            nextPlayerId = 3 - currentPlayerId;
        }
        LOGGER.info("The board now is:" + Arrays.toString( pits));
        
        //Check if the game is over
        boolean gameEnd = kalahGameRules.isGameOver(pits);
        if(gameEnd) {
        	pits = this.finalize(pits);
        	nextPlayerId = 3;
        }
        
        KalahBoard kalahBoard = new KalahBoard();
        kalahBoard.setPits(pits);
		
		KalahPlayer kalahPlayer = new KalahPlayer();
        kalahPlayer.setId(nextPlayerId);
        
        return this.setKalahGameResponse(kalahPlayer, kalahBoard, false, "");
    }
    
    /**
    * This method is to capture his and opponents stones.
    * @param pits, playerId, lastPitsIndex (index of the pit where move end)
    * 
    * when the last stone lands in an own empty pit, 
    * the player captures this stone and all stones in
    * the opposite pit (the other players' pit) and puts them in his own Kalah.
    */
    private int[] captureStones(int[] pits, int playerId, int lastPitsIndex) {
    	LOGGER.debug("KalahServiceImpl:: captureStones(): capture his and opponents stones");
    	if(playerId == 1) {
    		int opponentIndex = ((KalahConstants.HOUSE_INDEX_PLAYER1 - lastPitsIndex) * 2) + lastPitsIndex;
    		if( pits[opponentIndex] > 0) {
        		 pits[KalahConstants.HOUSE_INDEX_PLAYER1] +=   pits[lastPitsIndex] +  pits[opponentIndex];
        		 pits[opponentIndex] = 0;
        		 pits[lastPitsIndex] = 0;
    		}
    	} else {
    		int opponentIndex = KalahConstants.HOUSE_INDEX_PLAYER2 - lastPitsIndex - 1;
    		if( pits[opponentIndex] > 0) {
        		 pits[KalahConstants.HOUSE_INDEX_PLAYER2] +=   pits[lastPitsIndex] +  pits[opponentIndex];
        		 pits[opponentIndex] = 0;
        		 pits[lastPitsIndex] = 0;
    		}
    	}
    	return pits;
    }
    
    /**
    * This method is to finalize game calculations.
    * @param pits
    * 
    * The game is over as soon as one of the sides run out of stones
    * then The player who still has stones
	* in his/her pits keeps them and puts them in his/hers Kalah.
	* 
    */
    private int[] finalize(int[] pits) {
		LOGGER.debug("KalahServiceImpl:: finalize(): finalize game calculations");
		int player1HouseSum = Arrays.stream(Arrays.copyOfRange(pits, 0, KalahConstants.HOUSE_INDEX_PLAYER1)).sum();
		int player2HouseSum = Arrays.stream(Arrays.copyOfRange(pits, 7, KalahConstants.HOUSE_INDEX_PLAYER2)).sum();
		
		pits[KalahConstants.HOUSE_INDEX_PLAYER1] += player1HouseSum;
		pits[KalahConstants.HOUSE_INDEX_PLAYER2] += player2HouseSum;
		LOGGER.info("GAME OVER!!");
		
		return pits;
    }
    
    /**
    * This method is responsible to set kalah response.
    * @param error, errorMessage
    * @return KalahGame : Returns the game's board with current player id.
    * 
    */
    private KalahGame setKalahGameResponse(KalahPlayer kalahPlayer, KalahBoard kalahBoard, boolean error, String errorMessage) {
    	LOGGER.debug("KalahServiceImpl:: setKalahGameResponse(): sets response");
        KalahGame kalahGameResponse = new KalahGame();
        kalahGameResponse.setKalahPlayer(kalahPlayer);
        kalahGameResponse.setKalahBoard(kalahBoard);
        kalahGameResponse.setError(error);
        kalahGameResponse.setErrorMessage(errorMessage);
        return kalahGameResponse;
    }

}
