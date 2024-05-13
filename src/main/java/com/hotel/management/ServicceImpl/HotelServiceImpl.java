package com.hotel.management.ServicceImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotel.management.Dto.hotelDto;
import com.hotel.management.Entity.Category;
import com.hotel.management.Entity.Hotel;
import com.hotel.management.Entity.User;
import com.hotel.management.Repository.CategoryRepository;
import com.hotel.management.Repository.HotelRepository;
import com.hotel.management.Repository.UserRepository;
import com.hotel.management.Service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public hotelDto addHotel(hotelDto dto) throws IOException
	{
		Hotel hotel=new Hotel();
		String uuid=UUID.randomUUID().toString();
		hotel.setHotelId(uuid);
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String currentUsername=authentication.getName();
		User currentUser = userRepository.findByUsername(currentUsername);		
		if(currentUser!=null)
		{
			hotel.setUser(currentUser);
		}else
		{
			throw new UsernameNotFoundException("User not found with username: " + currentUsername);
		}
		
		hotel.setImg(dto.getImg().getBytes());
		
		Category category=categoryRepository.findById(dto.getCategoryId()).orElseThrow();
		hotel.setCategory(category);
		hotel.setDescription(dto.getDescription());
		hotel.setName(dto.getName());
		hotel.setPrice(dto.getPrice());	
		hotel.setImg(dto.getImg().getBytes());
		
		return hotelRepository.save(hotel).getDto();
	}

	public List<Hotel> getAllHotelWithUserId() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String currentUsername=authentication.getName();
		User currentUser = userRepository.findByUsername(currentUsername);		
		if(currentUser!=null)
		{
			return hotelRepository.findByUserUserId(currentUser.getUserId());
		}else
		{
			throw new UsernameNotFoundException("User not found with username: " + currentUsername);
		}
	}

	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}
}
