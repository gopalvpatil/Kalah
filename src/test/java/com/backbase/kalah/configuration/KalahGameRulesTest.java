package com.backbase.kalah.configuration;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.TestCase;

/**
* <h1>KalahGameRulesTest!</h1>
* This class is responsible to test Kalah game rules
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-19
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= "/servlet-context.xml")
@WebAppConfiguration
public class KalahGameRulesTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(KalahGameRulesTest.class);
	
	@Autowired
	KalahGameRules kalahGameRules;
	int[] pits = {2, 2, 3, 3, 2, 1, 29, 3, 2, 2, 2, 2, 1, 17};
	int[] lastScore = {0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 24};
	
	@Before
	public void setUp() throws Exception {
		LOGGER.debug("KalahBoardTest :: setUp");
	}
	
	@Test 
	public void testIsLastStoneLandsInOwnKalah() {
		assertTrue(kalahGameRules.isLastStoneLandsInOwnKalah(6, 1));
		assertTrue(kalahGameRules.isLastStoneLandsInOwnKalah(13, 2));
	}
	
	@Test 
	public void testIsLastStoneLandsOwnEmptyPit() {
		assertTrue(kalahGameRules.isLastStoneLandsOwnEmptyPit(pits, 5, 1));
		assertTrue(kalahGameRules.isLastStoneLandsOwnEmptyPit(pits, 12, 2));
	}
	
	@Test 
	public void testIsGameOver() {
		LOGGER.debug("KalahBoardTest :: testIsGameOver");
		int player1HouseSum = Arrays.stream(Arrays.copyOfRange(lastScore, 0, 6)).sum();
		int player2HouseSum = Arrays.stream(Arrays.copyOfRange(lastScore, 7, 13)).sum();
		
		if(player1HouseSum == 0 || player2HouseSum == 0) {
			LOGGER.info("GAME OVER!!");
		}
		assertTrue(kalahGameRules.isGameOver(lastScore));
	}
	
}
