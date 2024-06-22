package com.hotel.management.Service;

import java.io.IOException;
import java.util.List;

import com.hotel.management.Dto.BookAHotelDto;
import com.hotel.management.Dto.hotelDto;
import com.hotel.management.Entity.AdminBookingListDto;
import com.hotel.management.Entity.Hotel;

public interface HotelService {

	public hotelDto addHotel(hotelDto dto) throws IOException;

	public List<Hotel> getAllHotelWithUserId();

	public List<Hotel> getAllHotel();

	public hotelDto getHotelByHotelId(String hotelId);
	
	boolean BookAHotel (BookAHotelDto bookAHotelDto);
	
	List<BookAHotelDto> getBookingByUserId(String userId);
	
	public List<AdminBookingListDto> findAllData();
}
