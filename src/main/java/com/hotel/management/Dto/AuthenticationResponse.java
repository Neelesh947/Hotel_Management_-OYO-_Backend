package com.hotel.management.Dto;

import com.hotel.management.Enums.UserRoles;

import lombok.Data;

@Data
public class AuthenticationResponse {
    
	private String jwt;	
	private UserRoles  userRoles;	
	private String userId;
}
