package com.backbase.kalah.dto;

/**
* <h1>KalahResponse!</h1>
* This class is responsible to set KalahServiceImpl board response
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

public class KalahResponse {

    private int currentPlayerId;
    private String errorMessage;
    private boolean isError;
    
    
	/**
	 * @return the currentPlayerId
	 */
	public int getCurrentPlayerId() {
		return currentPlayerId;
	}
	/**
	 * @param currentPlayerId the currentPlayerId to set
	 */
	public void setCurrentPlayerId(int currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the isError
	 */
	public boolean isError() {
		return isError;
	}
	/**
	 * @param isError the isError to set
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}

}
