package com.test.Sample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Sample.Service.UserDetailsService;
import com.test.Sample.dto.AuthenticationRequest;
import com.test.Sample.dto.AuthenticationResponse;
import com.test.Sample.utility.JwtUtil;

@RestController
@RequestMapping(value="/token")
public class TokenController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping(value="/name")
	public String getName() {
		return "rajat";
	}

	@PostMapping(value="/generateToken")
	public ResponseEntity<?> getToken(@RequestBody AuthenticationRequest request) {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (BadCredentialsException bce) {
			throw new BadCredentialsException(bce.getMessage());
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUserName());
		final String token=jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok().body(new AuthenticationResponse(token));
	}
}
