package com.backbase.kalah.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.kalah.dto.KalahResponse;
import com.backbase.kalah.exception.KalahException;
import com.backbase.kalah.service.KalahService;

/**
* <h1>Kalah Rest Controller!</h1>
* This controller is responsible for initiating the
* kalah game
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

@RestController
@RequestMapping("/api")
public class KalahRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KalahRestController.class);
    
    @Autowired
    KalahService kalahService;
    
    /**
    * This service is called to initiate the game.
    * @param stonesPerPit : sets stones per pit
    * @return KalahResponse : Returns the game's board and current player id.
    * @exception IOException On input error.
    * @see IOException
    */
    @RequestMapping(value = "/initGame/{stonesPerPit}", method = RequestMethod.GET)
    @ResponseBody
    public KalahResponse initGame(@PathVariable int stonesPerPit) {
    	LOGGER.debug("KalahRestController:: initGame(): Initialise kalah Game");
    	KalahResponse kalahResponse = new KalahResponse();
    	try {
    		kalahResponse = kalahService.init();
    	} catch(KalahException e) {
    		LOGGER.error("Error occurred while initialising game. Please try again" , e);
            kalahResponse.setCurrentPlayerId(1);
            kalahResponse.setError(true);
            kalahResponse.setErrorMessage("Error occurred while initialising game. Please try again");
    	}
    	return kalahResponse;
    }

}