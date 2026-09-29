package com.securebank.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final CustomeUserDetailsService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer ")) {
			
			filterChain.doFilter(request, response);
			return;
		}
		String token = header.substring(7);
		
		try {
			String username = jwtService.extractUsername(token);
			System.out.println("Jwt Username "+username);
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails byUsername = userService.loadUserByUsername(username);
				System.out.println("Authorities = " + byUsername.getAuthorities());
				
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(byUsername,null,byUsername.getAuthorities());
			
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		filterChain.doFilter(request, response);
	}

}
