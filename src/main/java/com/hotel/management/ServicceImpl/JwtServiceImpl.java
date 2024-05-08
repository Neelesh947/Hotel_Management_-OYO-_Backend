package com.hotel.management.ServicceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotel.management.Repository.UserRepository;
import com.hotel.management.Service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

	@Autowired
	private UserRepository userRepoitory;
	
	public UserDetailsService userDetailsService(){
		return new UserDetailsService() {			
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userRepoitory.findFirstByUsername(username)
							.orElseThrow(()-> new UsernameNotFoundException("User not found"));
			}
		};
	}
}
