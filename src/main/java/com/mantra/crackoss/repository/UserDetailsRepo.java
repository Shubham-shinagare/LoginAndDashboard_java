package com.mantra.crackoss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mantra.crackoss.entity.User;

public interface UserDetailsRepo extends JpaRepository<User, String>{

	public Optional<User> findByEmail(String email);

}
