package com.mantra.crackoss.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // this is used for getter and setter of lombok
@Entity //its used for table entity
@Table(name = "login", catalog = "db_attendance") // its used for table in database
public class Login{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this used for auto generate id 
    private int id;
	
	@Column(name = "user_id") //its used for which column name present of this table
	private String userid;
	
	@Column(name = "password")
	private String password;

}
