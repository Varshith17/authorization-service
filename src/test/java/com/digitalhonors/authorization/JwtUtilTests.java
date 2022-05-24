package com.digitalhonors.authorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalhonors.authorization.config.JwtTokenUtil;
import com.digitalhonors.authorization.exception.ResourceNotFound;
import com.digitalhonors.authorization.payloadData.RequestData;
import com.digitalhonors.authorization.service.CustomerDetailsService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtUtilTests {

	@Autowired(required = true)
	JwtTokenUtil jwtUtil;
	
	@Autowired(required = true)
	private CustomerDetailsService customerDetailsService;

	@Test
	public void contextLoads() {

		assertNotNull(jwtUtil);

	}

	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YXJzaGl0aCIsImV4cCI6MTY1Mjg4Mjk2MCwiaWF0IjoxNjUyODY0OTYwfQ.t5KTacQx4xIf4i8YZ7UlPG2bvLn425YP3XCPA3oqfoZwPC7ryoPthqdlk5HTr1v2Pmvsox7aoRBy41qUcFEc2g";

	@Test
	public void extractUsernameTestSuccess() {

		assertEquals("varshith", jwtUtil.getUsernameFromToken(token));
	}

	@Test
	public void generateTokenTestSuccess() {
		RequestData userData = new RequestData("varshith", "1234");
		UserDetails userDetails = customerDetailsService.loadUserByUsername(userData.getUsername());
		String exampleToken = jwtUtil.generateToken(userDetails);
		assertNotNull(exampleToken);
	}

	@Test(expected = ResourceNotFound.class)
	public void generateTokenTestFail() {
		RequestData userData = new RequestData("vahith", "128u934");
		UserDetails userDetails = customerDetailsService.loadUserByUsername(userData.getUsername());
		String exampleToken = jwtUtil.generateToken(userDetails);
		assertNull(exampleToken);
	}

	@Test
	public void validateTokenTestSuccess() {
		RequestData userData = new RequestData("varshith", "1234");
		UserDetails userDetails = customerDetailsService.loadUserByUsername(userData.getUsername());
		assertTrue(jwtUtil.validateToken(token, userDetails));
	}

	@Test
	public void validateTokenTestFail() {
		RequestData userData = new RequestData("varshith", "12334");
		UserDetails userDetails = customerDetailsService.loadUserByUsername(userData.getUsername());
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXJzaGl0aCIsImV4cCI6MTY1Mjg5NTA0MSwiaWF0IjoxNjUyODc3MDQxfQ.gGGWtcs_C_vxfw_6bK4iKtcCGmiyTOnuTPys52wizXXc7jIoQoBVKcVcsTOX6H9gihSaMecDh7nDQZHrSroTMg";
		assertFalse(jwtUtil.validateToken(token, userDetails));	

	}
}
