package com.backbase.kalah.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.kalah.dto.KalahGame;
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
    * @return KalahGame : Returns the game's board and current player id.
    * @exception IOException On input error.
    * @see IOException
    */
    @RequestMapping(value = "/initGame/{stonesPerPit}", method = RequestMethod.GET)
    @ResponseBody
    public KalahGame initGame(@PathVariable int stonesPerPit) {
    	LOGGER.debug("KalahRestController:: initGame(): Initialise kalah Game");
    	KalahGame kalahResponse = new KalahGame();
    	try {
    		kalahResponse = kalahService.init(stonesPerPit);
    	} catch(KalahException e) {
    		LOGGER.error("Error occurred while initialising game. Please try again" , e);
            kalahResponse.setError(true);
            kalahResponse.setErrorMessage("Error occurred while initialising game. Please try again");
    	}
    	return kalahResponse;
    }
    
    /**
    * This service is called to make a move of stones in each pit.
    * @param currentPlayer
    * @return KalahGame : Returns the game's board and current player id.
    * @exception IOException On input error.
    * @see IOException
    */
    @RequestMapping(value = "/moveStones", method = RequestMethod.POST)
    @ResponseBody
    public KalahGame moveStones(@RequestBody KalahGame kalahGameRequest) {
    	LOGGER.debug("KalahRestController:: moveStones():  Sow Stones from pit index : " + kalahGameRequest.getSelectedPitsIndex());
    	KalahGame kalahGameResponse = new KalahGame();
    	kalahGameResponse = kalahService.validateMove(kalahGameRequest);
    	//if validation failed, return error response back to ui.
    	if(kalahGameResponse.isError()) {
            return kalahGameResponse;
    	} else {
    		try {
    			kalahGameResponse = kalahService.moveStones(kalahGameRequest);
        	} catch(KalahException e) {
        		LOGGER.error("Error occurred while playing game. Please try again" , e);
        		kalahGameResponse.setKalahBoard(kalahGameRequest.getKalahBoard());
        		kalahGameResponse.setKalahPlayer(kalahGameRequest.getKalahPlayer());
        		kalahGameResponse.setError(true);
        		kalahGameResponse.setErrorMessage("Error occurred while playing game. Please try again");
        	}
    	}
        return kalahGameResponse;
    }

}