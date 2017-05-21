package com.backbase.kalah.dto;

import java.io.Serializable;

/**
* <h1>KalahGame!</h1>
* This class is responsible to set Kalah game request and response
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

public class KalahGame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int selectedPitsIndex;
	private KalahPlayer kalahPlayer;
    private KalahBoard kalahBoard;
    private String errorMessage;
    private boolean isError;
    
    
	/**
	 * @return the selectedPitsIndex
	 */
	public int getSelectedPitsIndex() {
		return selectedPitsIndex;
	}
	/**
	 * @param selectedPitsIndex the selectedPitsIndex to set
	 */
	public void setSelectedPitsIndex(int selectedPitsIndex) {
		this.selectedPitsIndex = selectedPitsIndex;
	}
	/**
	 * @return the kalahPlayer
	 */
	public KalahPlayer getKalahPlayer() {
		return kalahPlayer;
	}
	/**
	 * @param kalahPlayer the kalahPlayer to set
	 */
	public void setKalahPlayer(KalahPlayer kalahPlayer) {
		this.kalahPlayer = kalahPlayer;
	}
	/**
	 * @return the kalahBoard
	 */
	public KalahBoard getKalahBoard() {
		return kalahBoard;
	}
	/**
	 * @param kalahBoard the kalahBoard to set
	 */
	public void setKalahBoard(KalahBoard kalahBoard) {
		this.kalahBoard = kalahBoard;
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