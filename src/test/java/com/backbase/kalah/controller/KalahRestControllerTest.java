package com.backbase.kalah.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.support.membermodification.MemberModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.backbase.kalah.dto.KalahPlayer;
import com.backbase.kalah.dto.KalahResponse;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.service.impl.KalahServiceImpl;
import com.google.gson.Gson;

/**
* <h1>Kalah Rest Controller Test!</h1>
* This controller is responsible to test Kalah Rest Controller
* kalah game
*
* @author Gopal Patil
* @version 1.0
* @since   2017-05-18
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= "/servlet-context.xml")
@WebAppConfiguration
@RestController
public class KalahRestControllerTest {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(KalahRestControllerTest.class);

	private MockMvc mockMvc;
	
	KalahRestController kalahRestController;
	
	@Autowired
	KalahService kalahService;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		kalahRestController = EasyMock.createMockBuilder(KalahRestController.class).createMock();
		kalahService = EasyMock.createMock(KalahServiceImpl.class);
		MemberModifier.field(KalahRestController.class, "kalahService").set(kalahRestController , kalahService);
	}
	
	@Test
	public void testInitGame() throws Exception {
		LOGGER.debug("KalahRestControllerTest:: testInitGame(): Test Initialise kalah Game");
		KalahResponse kalahResponse = new KalahResponse();
		kalahResponse.setCurrentPlayerId(1);
		kalahResponse.setError(false);
	
		kalahRestController = EasyMock.createMock(KalahRestController.class);
		EasyMock.expect(kalahRestController.initGame(6)).andReturn(kalahResponse).times(3);		
		EasyMock.replay(kalahRestController);
		
		assertNotNull(kalahRestController.initGame(6).getCurrentPlayerId());
		assertEquals(1, kalahRestController.initGame(6).getCurrentPlayerId());
	}
	
	@Test
    public void testInitGameApi() throws Exception {
		LOGGER.debug("KalahRestControllerTest:: testInitGameApi(): Test Initialise kalah Game API");
        KalahPlayer kalahPlayer = new KalahPlayer();
        kalahPlayer.setId(1);
        String json = new Gson().toJson(kalahPlayer);

        mockMvc.perform(
                post("/kalah/api/initGame/6")
                        .content(json)).andReturn();
    }
	
}
