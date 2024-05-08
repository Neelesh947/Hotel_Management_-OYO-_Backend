package com.hotel.management.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtService {

	public UserDetailsService userDetailsService();
}
