package com.backbase.kalah.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.backbase.kalah.util.KalahConstants;

/**
* <h1>KalahGameRules!</h1>
* This class is responsible for implementing all the game rules
* 
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-19
*/

@Component
public class KalahGameRules {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KalahGameRules.class);
	
    /**
    * This method is called to verify is player's last stone lands in his own Kalah.
    * @param lastPitsIndex, currentPlayerId
    * @return boolean : Returns true or false.
    * 
    */
	public boolean isLastStoneLandsInOwnKalah(int lastPitsIndex, int currentPlayerId) {
		LOGGER.debug("KalahGameRules:: isLastStoneLandsInOwnKalah(): Verify is player's last stone lands in his own Kalah");
    	if((currentPlayerId == 1 && lastPitsIndex == KalahConstants.HOUSE_INDEX_PLAYER1) ||
				(currentPlayerId == 2 && lastPitsIndex == KalahConstants.HOUSE_INDEX_PLAYER2)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
    * This method is called to verify is player's last stone lands in own empty pit.
    * @param pits, lastPitsIndex, currentPlayerId
    * @return boolean : Returns true or false.
    * 
    */
	public boolean isLastStoneLandsOwnEmptyPit(int[] pits, int lastPitsIndex, int currentPlayerId) {
		LOGGER.debug("KalahGameRules:: isLastStoneLandsOwnEmptyPit(): Verify is player's last stone lands in own empty pit");
    	if((currentPlayerId == 1 && lastPitsIndex < KalahConstants.HOUSE_INDEX_PLAYER1) ||
				(currentPlayerId == 2 && lastPitsIndex > KalahConstants.HOUSE_INDEX_PLAYER1)) {
    		if((pits[lastPitsIndex] - 1)  == 0) {
    			LOGGER.info("Last stone lands in players own empty pit "+ lastPitsIndex + ", for KalahPlayer:" + currentPlayerId);
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }

    /**
    * This method is called to verify is game over.
    * @param pits
    * @return boolean : Returns true or false. 
    * 
    * The game is over as soon as one of the sides run out of stones
    * then The player who still has stones
	* in his/her pits keeps them and puts them in his/hers Kalah.
    */
	public boolean isGameOver(int[] pits) {
		LOGGER.debug("KalahGameRules:: isGameOver(): Verify game is verify");
	    return ((pits[0] + pits[1] + pits[2] + pits[3] + pits[4] + pits[5]) == 0
	        || (pits[7] + pits[8] + pits[9] + pits[10] + pits[11] + pits[12]) == 0);
    }
}
