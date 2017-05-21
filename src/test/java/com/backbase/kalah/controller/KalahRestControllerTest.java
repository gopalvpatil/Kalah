package com.backbase.kalah.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.backbase.kalah.dto.KalahGame;
import com.backbase.kalah.dto.KalahPlayer;
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
		KalahGame kalahGameResponse = new KalahGame();
		KalahPlayer kalahPlayer = new KalahPlayer();
		kalahPlayer.setId(1);
		
		kalahGameResponse.setKalahPlayer(kalahPlayer);
		kalahGameResponse.setError(false);
	
		kalahRestController = EasyMock.createMock(KalahRestController.class);
		EasyMock.expect(kalahRestController.initGame(6)).andReturn(kalahGameResponse).times(3);		
		EasyMock.replay(kalahRestController);
		
		assertNotNull(kalahRestController.initGame(6).getKalahPlayer().getId());
		assertEquals(1, kalahRestController.initGame(6).getKalahPlayer().getId());
	}
	
	@Test
    public void testInitGameApi() throws Exception {
		LOGGER.debug("KalahRestControllerTest:: testInitGameApi(): Test Initialise kalah Game API");
        KalahPlayer kalahPlayer = new KalahPlayer();
        kalahPlayer.setId(1);
        String json = new Gson().toJson(kalahPlayer);

        mockMvc.perform(
                post("/kalah/api/initGame/6").content(json))
        		.andExpect(status().isOk())
                .andReturn();
    }
	
	@Test
	public void testMoveStones() throws Exception {
		LOGGER.debug("KalahRestControllerTest:: testMoveStones(): Test Move Stones");

        KalahPlayer kalahPlayer = new KalahPlayer();
        kalahPlayer.setId(1);
        
		KalahGame kalahGameResponse = new KalahGame();
		kalahGameResponse.setKalahPlayer(kalahPlayer);
		kalahGameResponse.setError(false);
        
        KalahGame kalahGameRequest = new KalahGame();
        kalahGameRequest.setSelectedPitsIndex(0);
        kalahGameRequest.setKalahPlayer(kalahPlayer);
	
		kalahRestController = EasyMock.createMock(KalahRestController.class);
		EasyMock.expect(kalahRestController.moveStones(kalahGameRequest)).andReturn(kalahGameResponse).times(3);		
		EasyMock.replay(kalahRestController);
		
		assertNotNull(kalahRestController.moveStones(kalahGameRequest).getKalahPlayer().getId());
		assertEquals(1, kalahRestController.moveStones(kalahGameRequest).getKalahPlayer().getId());
	}
	
	@Test
    public void testMoveStonesApi() throws Exception {
		LOGGER.debug("KalahRestControllerTest:: testMoveStonesApi(): Test move stones API");
        KalahPlayer kalahPlayer = new KalahPlayer();
        kalahPlayer.setId(1);
        
        KalahGame kalahGameRequest = new KalahGame();
        kalahGameRequest.setSelectedPitsIndex(0);
        kalahGameRequest.setKalahPlayer(kalahPlayer);
        String json = new Gson().toJson(kalahGameRequest);

        mockMvc.perform(
                post("/kalah/api/moveStones").content(json))
        		.andExpect(status().isOk())
                .andReturn();
    }
	
}
