package com.hotel.management.Dto;

import com.hotel.management.Enums.UserRoles;

import lombok.Data;

@Data
public class SignUpRequest {

	private String username;
	private String password;
	private String name;
	private UserRoles userRoles;
	private String email;
	private String phone;
}
