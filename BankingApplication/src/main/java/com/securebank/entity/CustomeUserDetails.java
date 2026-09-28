package com.securebank.entity;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomeUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private final User user;
	
	@Override
	public @Nullable String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}
	
	@Override
	public boolean isEnabled() {
		
		return user.isEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return user.getRoles().stream().map(role ->
		new SimpleGrantedAuthority("ROLE_"+role.name())).toList();
	}
}
