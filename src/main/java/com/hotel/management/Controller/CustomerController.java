package com.hotel.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.management.Dto.BookAHotelDto;
import com.hotel.management.Dto.hotelDto;
import com.hotel.management.Entity.Hotel;
import com.hotel.management.Service.HotelService;

@RestController
@RequestMapping("/customer/")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private HotelService hotelService;
	
	@GetMapping("/get-hotel-list")
	public ResponseEntity<List<Hotel>> getAllHotelList()
	{
		List<Hotel> dto=this.hotelService.getAllHotel();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@PostMapping("/book-hotel")
	public ResponseEntity<Void> BookAHotelByTheUser(@RequestBody BookAHotelDto bookAHotelDto)
	{
		boolean success= hotelService.BookAHotel(bookAHotelDto);
		if(success)
		{
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<hotelDto> getHotelByHotelId(@PathVariable String hotelId)
	{
		hotelDto hotelDto=hotelService.getHotelByHotelId(hotelId);
		if(hotelDto==null) 
			return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.OK).body(hotelDto);
	}
	
	@GetMapping("/hotel/booking/{userId}")
	public ResponseEntity<List<BookAHotelDto>> getBookingByUserId(@PathVariable String userId)
	{
		List<BookAHotelDto> booklist=hotelService.getBookingByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(booklist);
	}
}
