package com.backbase.kalah.service;

import java.io.IOException;
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

import com.backbase.kalah.dto.KalahBoard;
import com.backbase.kalah.dto.KalahGame;
import com.backbase.kalah.dto.KalahPlayer;

import junit.framework.TestCase;

/**
* <h1>KalahServiceTest!</h1>
* This test class is for KalahService
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= "/servlet-context.xml")
@WebAppConfiguration
public class KalahServiceTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(KalahServiceTest.class);
	
	@Autowired
	KalahService kalahService;
	
    private KalahPlayer kalahPlayer;
    private KalahBoard kalahBoard;
    
	int[] expectedPits = {2, 2, 3, 3, 2, 1, 29, 3, 2, 2, 2, 2, 1, 17};
	
	@Before
	public void setUp() throws Exception {
		LOGGER.debug("KalahServiceTest :: setUp");
		int[] pits = {6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
		kalahBoard = new KalahBoard();
		kalahBoard.setPits(pits);
		
		kalahPlayer = new KalahPlayer();
		kalahPlayer.setId(1);
	}
	
	@Test
	public void testInit() throws IOException {
		LOGGER.debug("KalahServiceTest :: testInit");
		KalahGame kalahResponse = kalahService.init(6);
		assertEquals(kalahPlayer.getId(), kalahResponse.getKalahPlayer().getId());
		assertEquals(Arrays.toString(kalahBoard.getPits()), Arrays.toString(kalahResponse.getKalahBoard().getPits()));
	}
	
	@Test
	public void testValidateMove() throws IOException {
		LOGGER.debug("KalahServiceTest :: testValidateMove");
		KalahGame kalahGameRequest = new KalahGame();
		kalahGameRequest.setSelectedPitsIndex(12);
		kalahGameRequest.setKalahBoard(kalahBoard);
		kalahGameRequest.setKalahPlayer(kalahPlayer);
		assertTrue(kalahService.validateMove(kalahGameRequest).isError());
	}
	
	@Test
	public void testMoveStones() throws IOException {
		LOGGER.debug("KalahServiceTest :: moveStones");
		int[] currentPits = {0, 0, 1, 1, 1, 0, 29, 2, 1, 1, 1, 1, 18, 15};
		
		kalahBoard.setPits(currentPits);
		kalahPlayer.setId(2);
		
		KalahGame kalahGameRequest = new KalahGame();
		kalahGameRequest.setSelectedPitsIndex(12);
		kalahGameRequest.setKalahBoard(kalahBoard);
		kalahGameRequest.setKalahPlayer(kalahPlayer);
		
		LOGGER.debug("Current Game KalahBoard: " + Arrays.toString(currentPits));
		KalahGame kalahGameResponse = kalahService.moveStones(kalahGameRequest);
		
		String pitsStr =  Arrays.toString(kalahGameResponse.getKalahBoard().getPits());
		
		assertEquals(Arrays.toString(expectedPits), pitsStr);
		LOGGER.debug("Changed Game KalahBoard: " + pitsStr);
	}
	
}
