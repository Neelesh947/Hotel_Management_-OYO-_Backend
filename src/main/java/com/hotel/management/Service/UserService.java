package com.hotel.management.Service;

import com.hotel.management.Dto.SignUpRequest;
import com.hotel.management.Dto.UserDto;

public interface UserService {

	public boolean hasUserExistWithUsername(String username);
	
	public UserDto createCustomer(SignUpRequest signuprequest);
}
