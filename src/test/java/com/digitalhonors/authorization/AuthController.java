package com.digitalhonors.authorization;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.digitalhonors.authorization.controller.AuthenticationController;
import com.digitalhonors.authorization.payloadData.RequestData;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthController {

	private static String token= "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YXJzaGl0aCIsImV4cCI6MTY1Mjg4Mjk2MCwiaWF0IjoxNjUyODY0OTYwfQ.t5KTacQx4xIf4i8YZ7UlPG2bvLn425YP3XCPA3oqfoZwPC7ryoPthqdlk5HTr1v2Pmvsox7aoRBy41qUcFEc2g";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AuthenticationController authController;
	
	@Test
	public void contextLoads() {

		assertNotNull(authController);

	}

	@Test
	public void loginTestSuccess() throws Exception {
		RequestData admin = new RequestData("varshith","1234");

		ResultActions actions = mockMvc
				.perform(post("/auth/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isOk());
	}

	@Test
	public void loginTestFail() throws Exception {
		RequestData admin = new RequestData("varshith","12345");

		ResultActions actions = mockMvc
				.perform(post("/auth/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isUnauthorized());
	}

	@Test
	public void validateTestSuccess() throws Exception {
		ResultActions actions = mockMvc.perform(get("/auth/authorize").header("Authorization",token));
		actions.andExpect(status().isOk());
	}

	@Test
	public void validateTestFail() throws Exception {
		ResultActions actions = mockMvc.perform(get("/auth/authorize").header("Authorization", "randomToken"));

		actions.andExpect(status().isUnauthorized());

	}

	public static String asJsonString(RequestData admin) {
		try {
			return new ObjectMapper().writeValueAsString(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
