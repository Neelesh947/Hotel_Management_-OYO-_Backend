package com.hotel.management.ServicceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotel.management.Dto.BookAHotelDto;
import com.hotel.management.Dto.hotelDto;
import com.hotel.management.Entity.AdminBookingListDto;
import com.hotel.management.Entity.BookAHotel;
import com.hotel.management.Entity.Category;
import com.hotel.management.Entity.Hotel;
import com.hotel.management.Entity.User;
import com.hotel.management.Enums.BookHotelStatus;
import com.hotel.management.Repository.AdminBookinJDBC;
import com.hotel.management.Repository.BookAHotelRepository;
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
	
	@Autowired
	private AdminBookinJDBC adminBookinJDBC;
	
	@Autowired
	private BookAHotelRepository bookAHotelRepository;
	
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

	public hotelDto getHotelByHotelId(String hotelId) {
		Optional<Hotel> hotel=hotelRepository.findById(hotelId);
		return hotel.map(Hotel::getDto).orElse(null);
	}

	public boolean BookAHotel(BookAHotelDto bookAHotelDto) {
		
		Optional<Hotel> optionalHotel=hotelRepository.findById(bookAHotelDto.getHotelId());
		Optional<User> optionalUser=userRepository.findById(bookAHotelDto.getUserId());
		
		String uuid=UUID.randomUUID().toString();
		
		if(optionalHotel.isPresent() && optionalUser.isPresent())
		{
			Hotel existingHotel=optionalHotel.get();
			BookAHotel bookAHotel=new BookAHotel();
			bookAHotel.setUser(optionalUser.get());
			bookAHotel.setHotel(existingHotel);
			bookAHotel.setBookHotelStatus(BookHotelStatus.PENDING);
			bookAHotel.setBookingId(uuid);
			bookAHotel.setFromDate(bookAHotelDto.getFromDate());
			bookAHotel.setToDate(bookAHotelDto.getToDate());
			
			long diffInMilliSeconds=bookAHotelDto.getToDate().getTime() - bookAHotelDto.getFromDate().getTime();
			long days= TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
			bookAHotel.setDays(days);
			bookAHotel.setPrice(existingHotel.getPrice()*days);
			
			bookAHotelRepository.save(bookAHotel);
			return true;
		}
		return false;
	}

	public List<BookAHotelDto> getBookingByUserId(String userId) {
		return bookAHotelRepository.findAllByUserUserId(userId).stream()
				.map(BookAHotel::bookAHotelDto).collect(Collectors.toList());
	}	
	
	public List<AdminBookingListDto> findAllData() {
        return adminBookinJDBC.findAllData();
    }
	
	
}
