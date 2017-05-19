package com.backbase.kalah.service;

import com.backbase.kalah.dto.KalahResponse;
import com.backbase.kalah.exception.KalahException;

/**
* <h1>KalahService!</h1>
* This interface is responsible for initiating the
* kalah game
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/
public interface KalahService {

	public KalahResponse init() throws KalahException ;
	
}
