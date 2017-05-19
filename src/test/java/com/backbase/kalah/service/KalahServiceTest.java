package com.backbase.kalah.service;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backbase.kalah.dto.KalahResponse;
import com.backbase.kalah.service.impl.KalahServiceImpl;

import junit.framework.TestCase;

/**
* <h1>KalahServiceTest!</h1>
* This test class is for KalahService
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/

public class KalahServiceTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(KalahServiceTest.class);
	
	@Before
	public void setUp() throws Exception {
		LOGGER.debug("KalahServiceTest :: setUp");
	}
	
	@Test
	public void testInit() throws IOException {
		LOGGER.debug("KalahServiceTest :: testInit");
		KalahServiceImpl kalahServiceImpl = new KalahServiceImpl();
		KalahResponse kalahResponse = kalahServiceImpl.init();
		assertEquals(1, kalahResponse.getCurrentPlayerId());
	}
	
}
