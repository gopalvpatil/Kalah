package com.backbase.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* <h1>KalahException!</h1>
* This is the Generic Exception to handler both Application Exception and
* Validation Exception
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to process the given request")
public class KalahException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public KalahException() {
	}

	public KalahException(String message) {
		super(message);
	}

	public KalahException(Throwable cause) {
		super(cause);
	}

	public KalahException(String message, Throwable throwable) {
		super(message, throwable);
	}
}