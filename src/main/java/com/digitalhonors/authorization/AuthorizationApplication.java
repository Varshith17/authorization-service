package com.digitalhonors.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.digitalhonors.authorization.entity.User;
import com.digitalhonors.authorization.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class AuthorizationApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("varshith", "1234"));
		userRepository.save(new User("harshith","12345" ));
		userRepository.save(new User("chandan", "stark"));
		userRepository.save(new User("tejas","123456"));
	}
}
