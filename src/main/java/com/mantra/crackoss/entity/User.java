package com.mantra.crackoss.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "user_details", catalog = "db_attendance")
public class User implements UserDetails{
	
	@Id
	@Column(name = "user_id") //its used for which column name present of this table
	private String userid;
	
	@Column(name = "name") //its used for which column name present of this table
	private String name;
	
	@Column(name = "email") //its used for which column name present of this table
	private String email;
	
	@Column(name = "password")
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
}
