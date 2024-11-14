package com.mantra.crackoss.entity;

import java.util.Collection;
import java.util.Collections;

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
        // If you have roles/authorities to assign, you can return them here.
        // For example, if you have roles stored in the database, you would fetch and return them here.
        // For now, returning an empty collection since authorities are not defined.
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.email;  // Return the email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        // Return true if account is not expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Return true if account is not locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Return true if credentials are not expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Return true if the user is enabled
        return true;
    }
}
