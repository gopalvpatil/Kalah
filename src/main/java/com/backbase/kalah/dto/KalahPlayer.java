package com.backbase.kalah.dto;

import java.io.Serializable;

/**
* <h1>KalahPlayer!</h1>
* This pojo class is set Kalah Player
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/
public class KalahPlayer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		KalahPlayer other = (KalahPlayer) obj;
		
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "KalahPlayer [id=" + id + "]";
	}
	
}