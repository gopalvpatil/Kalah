package com.backbase.kalah.util;

/**
* <h1>KalahConstants!</h1>
* This class is responsible to set Kalah game constants
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-19
*/

public class KalahConstants {
	//Number of Pits.
	public static final int NUM_OF_PITS_PER_PLAYER = 6;
	public static final int TOTAL_NUM_OF_PITS = NUM_OF_PITS_PER_PLAYER * 2 + 2;
	
	//Index of players house.
	public static final int HOUSE_INDEX_PLAYER1 = NUM_OF_PITS_PER_PLAYER;
	public static final int HOUSE_INDEX_PLAYER2 = NUM_OF_PITS_PER_PLAYER * 2 + 1;
}
