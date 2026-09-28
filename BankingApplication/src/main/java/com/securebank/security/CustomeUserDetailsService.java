package com.securebank.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securebank.entity.CustomeUserDetails;
import com.securebank.entity.User;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService{

	private final UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email).orElseThrow(() ->
		new ResourceNotFoundException("User not found on username "+email));
		
		return new CustomeUserDetails(user);
	}

}
