package com.hfaria.micronotesback.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hfaria.micronotesback.authentication.dto.LoginRequestDTO;
import com.hfaria.micronotesback.authentication.dto.RegistryRequestDTO;
import com.hfaria.micronotesback.model.User;
import com.hfaria.micronotesback.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEnconder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTService jwtService;
	
	
	public boolean registerNewUser(RegistryRequestDTO registryRequest) {
		User newUser = new User();
		newUser.setFirstName(registryRequest.getFirstName());
		newUser.setLastName(registryRequest.getLastName());
		newUser.setEmail(registryRequest.getEmail());
		newUser.setEncriptedPassword(encriptPassword(registryRequest.getPassword()));
		
		try {
			userRepository.save(newUser).toString();
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
	public boolean authenticate() {
		return true;
	}

	
	private String encriptPassword(String password) {
		return passwordEnconder.encode(password);
	}

	public String login(LoginRequestDTO loginRequest) {
		Authentication authenticationObj = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticationObj);
		return jwtService.getToken(authenticationObj);
	}
	
	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser(){
	    
	    return Optional.of(
	            ((org.springframework.security.core.userdetails.User)
	            SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
	}

}
