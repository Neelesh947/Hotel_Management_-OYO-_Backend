package com.hotel.management.Dto;

import com.hotel.management.Enums.UserRoles;

import lombok.Data;

@Data
public class UserDto {

	private String userId;
	private String username;
	private String name;
	private String password;
	private String phone;
	private String email;
	private UserRoles userRoles;
}
