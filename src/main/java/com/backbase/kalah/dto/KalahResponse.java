package com.backbase.kalah.dto;

import java.io.Serializable;

/**
* <h1>KalahResponse!</h1>
* This class is responsible to set Kalah board response
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

public class KalahResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private KalahPlayer kalahPlayer;
    private KalahBoard kalahBoard;
    private String errorMessage;
    private boolean isError;
    
    
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + (isError ? 1231 : 1237);
		result = prime * result + ((kalahBoard == null) ? 0 : kalahBoard.hashCode());
		result = prime * result + ((kalahPlayer == null) ? 0 : kalahPlayer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		KalahResponse other = (KalahResponse) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (isError != other.isError)
			return false;
		if (kalahBoard == null) {
			if (other.kalahBoard != null)
				return false;
		} else if (!kalahBoard.equals(other.kalahBoard))
			return false;
		if (kalahPlayer == null) {
			if (other.kalahPlayer != null)
				return false;
		} else if (!kalahPlayer.equals(other.kalahPlayer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KalahResponse [kalahPlayer=" + kalahPlayer + ", kalahBoard=" + kalahBoard + ", errorMessage="
				+ errorMessage + ", isError=" + isError + "]";
	}

}