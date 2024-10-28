package com.mantra.crackoss.controller;

import java.util.List;

import org.apache.el.parser.AstNull;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mantra.crackoss.entity.User;
import com.mantra.crackoss.model.JWTRequest;
import com.mantra.crackoss.model.JWTResponse;
import com.mantra.crackoss.model.LoginRequestDTO;
import com.mantra.crackoss.model.LoginResponseDTO;
import com.mantra.crackoss.security.JWTHelper;
import com.mantra.crackoss.services.LoginService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTHelper helper;
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	
    @ApiResponses(value = {@ApiResponse(code = 200, response = LoginRequestDTO.class, message = "Login successfully. "),
		@ApiResponse(code = 401, response = AstNull.class, message = "authentication failed"),
		@ApiResponse(code = 403, response = AstNull.class, message = "access not allowed"),
		@ApiResponse(code = 204, response = AstNull.class, message = "No content")})
    @PostMapping(path = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(
	    @RequestBody JWTRequest jwtRequest){
    	this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
    	logger.info("Creating login user ");
	UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
	String token = helper.generateToken(userDetails);
	JWTResponse jwtResponse = new JWTResponse();
	jwtResponse.setJwtToken(token);
	jwtResponse.setUserName(userDetails.getUsername());
	return ResponseEntity.ok(jwtResponse);
    }
    
    private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password !!");
		}
	}
    
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
		return "Credential invalid !!!";
    }

	@ApiResponses(value = {@ApiResponse(code = 200, response = LoginRequestDTO.class, message = "Login successfully. "),
    		@ApiResponse(code = 401, response = AstNull.class, message = "authentication failed"),
    		@ApiResponse(code = 403, response = AstNull.class, message = "access not allowed"),
    		@ApiResponse(code = 204, response = AstNull.class, message = "No content")})
        @GetMapping(path = "/home/user", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<LoginResponseDTO>> getUser(){
		List<LoginResponseDTO> loginResponseDTOList = loginService.getUsers();
		return ResponseEntity.ok(loginResponseDTOList);
        }
	
	@ApiResponses(value = {@ApiResponse(code = 200, response = User.class, message = "User create successfully. "),
			@ApiResponse(code = 401, response = AstNull.class, message = "authentication failed"),
			@ApiResponse(code = 403, response = AstNull.class, message = "access not allowed"),
			@ApiResponse(code = 204, response = AstNull.class, message = "No content")})
	    @PostMapping(path = "/create-user", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> createUsers(
		    @RequestBody User user){
		User users = loginService.createUser(user);
		return ResponseEntity.ok(users);
	    }

}
