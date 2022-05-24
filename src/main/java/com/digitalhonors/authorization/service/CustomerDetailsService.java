package com.digitalhonors.authorization.service;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digitalhonors.authorization.entity.User;
import com.digitalhonors.authorization.exception.ResourceNotFound;
import com.digitalhonors.authorization.repository.UserRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			LOGGER.info("STARTED - loadUserByUsername");
			User user = userRepository.findByUsername(username);
			LOGGER.info("END - loadUserByUsername");
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		}
		catch(Exception e){
			LOGGER.error("ERROR-username not found");
			throw new ResourceNotFound(username);
		}

	}

}
