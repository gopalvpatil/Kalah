package com.backbase.kalah.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
* <h1>KalahBoard!</h1>
* This pojo class sets kalah game board pits
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-19
*/
public class KalahBoard implements Serializable {

	private static final long serialVersionUID = 1L;
	public int[] pits;

	/**
	 * @return the pits
	 */
	public int[] getPits() {
		return pits;
	}

	/**
	 * @param pits the pits to set
	 */
	public void setPits(int[] pits) {
		this.pits = pits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pits);
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
		
		KalahBoard other = (KalahBoard) obj;
		if (!Arrays.equals(pits, other.pits)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "KalahBoard [pits=" + Arrays.toString(pits) + "]";
	}
	
}