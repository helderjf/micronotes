package com.hfaria.micronotesback.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfaria.micronotesback.authentication.dto.LoginRequestDTO;
import com.hfaria.micronotesback.authentication.dto.RegistryRequestDTO;
import com.hfaria.micronotesback.authentication.service.AuthenticationService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegistryRequestDTO registryRequest) {
		if (authService.registerNewUser(registryRequest)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDTO loginRequest) {
		return authService.login(loginRequest);
	}

}
