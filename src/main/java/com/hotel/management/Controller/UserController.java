package com.hotel.management.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.management.Dto.AuthenticationRequest;
import com.hotel.management.Dto.AuthenticationResponse;
import com.hotel.management.Dto.SignUpRequest;
import com.hotel.management.Dto.UserDto;
import com.hotel.management.Dto.hotelDto;
import com.hotel.management.Entity.Hotel;
import com.hotel.management.Entity.User;
import com.hotel.management.JwtUtils.JwtUtils;
import com.hotel.management.Repository.UserRepository;
import com.hotel.management.Service.HotelService;
import com.hotel.management.Service.JwtService;
import com.hotel.management.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService  userService;	

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService userJwtService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("create-user")
	public ResponseEntity<?> signUprequest(@RequestBody SignUpRequest signuprequest)
	{
		if(userService.hasUserExistWithUsername(signuprequest.getUsername()))
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User-Name already exist");
		}		
		UserDto user= userService.createCustomer(signuprequest);
		if(user==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went Wrong");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PostMapping("login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
				BadCredentialsException, DisabledException, UsernameNotFoundException
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e)
		{
			throw new BadCredentialsException("Incorrect Username or password");
		}
		
		final UserDetails userDetails=userJwtService.userDetailsService().loadUserByUsername(authenticationRequest.getUsername());
		Optional<User> optionalUser=userRepository.findFirstByUsername(userDetails.getUsername());
		
		final String jwt=jwtUtils.generateToken(userDetails);
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		if(optionalUser.isPresent())
		{
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserId(optionalUser.get().getUserId());
			authenticationResponse.setUserRoles(optionalUser.get().getUserRoles()); 
		}
		
		return authenticationResponse;
	}
	
	@GetMapping("All-hotel-list")
	public ResponseEntity<List<Hotel>> getAllHotelList()
	{
		List<Hotel> dto=this.hotelService.getAllHotel();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping("get-hotel-by-id/{hotelId}")
	public ResponseEntity<hotelDto> getHotelDetails(@PathVariable("hotelId") String hotelId) {
	    hotelDto dto = this.hotelService.getHotelByHotelId(hotelId);
	    if (dto != null) {
	        return ResponseEntity.ok(dto);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
