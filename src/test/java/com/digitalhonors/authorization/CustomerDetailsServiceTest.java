package com.digitalhonors.authorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalhonors.authorization.exception.ResourceNotFound;
import com.digitalhonors.authorization.service.CustomerDetailsService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.digitalhonors.authorization.service"})
@AutoConfigureMockMvc
public class CustomerDetailsServiceTest {

	@Autowired(required = true)
	private CustomerDetailsService customerDetailsService;

	@Test
	public void contextLoads() {
		assertNotNull(customerDetailsService);
	}

	@Test
	public void loadUserByUsernameTestSuccess() {

		assertEquals("varshith", customerDetailsService.loadUserByUsername("varshith").getUsername());
	}

	@Test(expected = ResourceNotFound.class)
	public void loadUserByUsernameTestFail() {
		assertEquals("randomUser", customerDetailsService.loadUserByUsername("randomUser").getUsername());
	}
}
