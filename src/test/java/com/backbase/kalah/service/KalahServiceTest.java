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
import com.backbase.kalah.dto.KalahPlayer;
import com.backbase.kalah.dto.KalahResponse;

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
		KalahResponse kalahResponse = kalahService.init(6);
		assertEquals(kalahPlayer.getId(), kalahResponse.getKalahPlayer().getId());
		assertEquals(Arrays.toString(kalahBoard.getPits()), Arrays.toString(kalahResponse.getKalahBoard().getPits()));
	}
	
	@Test
	public void testValidateMove() throws IOException {
		LOGGER.debug("KalahServiceTest :: testValidateMove");
		assertTrue(kalahService.validateMove(kalahPlayer, kalahBoard, 12, 1).isError());
	}
	
}
