package com.hotel.management.ServicceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.management.Dto.SignUpRequest;
import com.hotel.management.Dto.UserDto;
import com.hotel.management.Entity.User;
import com.hotel.management.Enums.UserRoles;
import com.hotel.management.Repository.UserRepository;
import com.hotel.management.Service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	public UserDto createCustomer(SignUpRequest signuprequest) {
		// TODO Auto-generated method stub
		String uuid=UUID.randomUUID().toString();
		User user=new User();
		user.setUserId(uuid);
		user.setName(signuprequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signuprequest.getPassword()));
		user.setUsername(signuprequest.getUsername());
		user.setPhone(signuprequest.getPhone());
		user.setEmail(signuprequest.getEmail());
		if(signuprequest.getUserRoles()==UserRoles.ADMIN)
		{
			user.setUserRoles(UserRoles.ADMIN);
		}
		else {
			user.setUserRoles(UserRoles.USER);
		}
		User createdUser=userRepository.save(user);
		UserDto userDto=new UserDto();
		userDto.setEmail(createdUser.getEmail());
		userDto.setUserId(createdUser.getUserId());
		userDto.setName(createdUser.getName());
		userDto.setUsername(createdUser.getUsername());
		userDto.setPhone(createdUser.getPhone());
		userDto.setUserRoles(createdUser.getUserRoles());
		userDto.setPassword(createdUser.getPassword());
		return userDto;
	}
	
	public boolean hasUserExistWithUsername(String username)
	{
		return userRepository.findFirstByUsername(username).isPresent();
	}
}
