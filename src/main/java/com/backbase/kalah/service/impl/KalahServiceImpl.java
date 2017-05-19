package com.backbase.kalah.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.backbase.kalah.dto.KalahResponse;
import com.backbase.kalah.exception.KalahException;
import com.backbase.kalah.service.KalahService;

/**
* <h1>KalahServiceImpl!</h1>
* This service is responsible for initiating the
* kalah game
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

@Service("kalahService")
public class KalahServiceImpl implements KalahService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KalahServiceImpl.class);
    
    public static int currentPlayerId;

    /**
    * This method is called to initiate the game.
    * @return KalahResponse : Returns the game's board with current player id.
    * @exception KalahException On input error.
    * @see KalahException
    */
    public KalahResponse init() throws KalahException {
    	LOGGER.debug("KalahServiceImpl:: init(): Initialise kalah Game");
    	currentPlayerId = 1;
        return this.setKalahResponse(false, "");
    }
    
    /**
    * This method is responsible to set kalah response.
    * @param error, errorMessage
    * @return KalahResponse : Returns the game's board with current player id.
    * 
    */
    private KalahResponse setKalahResponse(boolean error, String errorMessage) {
        KalahResponse kalahResponse = new KalahResponse();
		kalahResponse.setCurrentPlayerId(currentPlayerId);
        return kalahResponse;
    }

}
