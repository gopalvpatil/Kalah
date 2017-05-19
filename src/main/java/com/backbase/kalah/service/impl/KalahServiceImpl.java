package com.backbase.kalah.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.backbase.kalah.dto.KalahBoard;
import com.backbase.kalah.dto.KalahPlayer;
import com.backbase.kalah.dto.KalahResponse;
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
    
    private KalahPlayer kalahPlayer;
    private KalahBoard kalahBoard;

    /**
    * This method is called to initiate the game.
    * @param stonesPerPit
    * @return KalahResponse : Returns the game's board with current player id.
    * @exception KalahException On input error.
    * @see KalahException
    */
    public KalahResponse init(int stonesPerPit) throws KalahException {
    	LOGGER.debug("KalahServiceImpl:: init(): Initialise kalah Game");
    	
		int[] pits = new int[KalahConstants.TOTAL_NUM_OF_PITS];  
		Arrays.fill(pits, stonesPerPit);
		pits[KalahConstants.HOUSE_INDEX_PLAYER1] = 0;
		pits[KalahConstants.HOUSE_INDEX_PLAYER2] = 0;
		
		kalahBoard = new KalahBoard();
		kalahBoard.setPits(pits);
		
		kalahPlayer = new KalahPlayer();
		kalahPlayer.setId(1);
    	
        return this.setKalahResponse(kalahPlayer, kalahBoard, false, "");
    }
    
    /**
    * This method is responsible to set kalah response.
    * @param error, errorMessage
    * @return KalahResponse : Returns the game's board with current player id.
    * 
    */
    private KalahResponse setKalahResponse(KalahPlayer kalahPlayer, KalahBoard kalahBoard, boolean error, String errorMessage) {
        KalahResponse kalahResponse = new KalahResponse();
		kalahResponse.setKalahPlayer(kalahPlayer);
		kalahResponse.setKalahBoard(kalahBoard);
		kalahResponse.setError(error);
		kalahResponse.setErrorMessage(errorMessage);
        return kalahResponse;
    }

}
