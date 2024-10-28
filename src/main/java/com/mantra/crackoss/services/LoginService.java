package com.mantra.crackoss.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mantra.crackoss.entity.Login;
import com.mantra.crackoss.entity.User;
import com.mantra.crackoss.model.LoginRequestDTO;
import com.mantra.crackoss.model.LoginResponseDTO;
import com.mantra.crackoss.repository.UserDetailsRepo;

@Service
public class LoginService {
	
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public LoginResponseDTO CreateLoginUser(LoginRequestDTO loginRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<LoginResponseDTO> getUsers() {
		List<User> users = userDetailsRepo.findAll();
		
		ArrayList<LoginResponseDTO> loginResponseDTOList = new ArrayList<LoginResponseDTO>();
		
		for(User user : users) {
			LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
			loginResponseDTO.setEmail(user.getEmail());
			loginResponseDTO.setPassword(user.getPassword());
			loginResponseDTOList.add(loginResponseDTO);
		}	
		return loginResponseDTOList ;
	}
	
	public User createUser(User user) {
		user.setUserid(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDetailsRepo.save(user);
		
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Login login = userDetailsRepo.findByUserid(username);
//		return login;
//	}

}
