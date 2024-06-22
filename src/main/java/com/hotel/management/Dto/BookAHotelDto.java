package com.hotel.management.Dto;

import java.util.Date;

import com.hotel.management.Enums.BookHotelStatus;

import lombok.Data;

@Data
public class BookAHotelDto {

	private String bookingId;
	private Date toDate;
	private Date fromDate;
	private Long days;
	private Long price;
	private BookHotelStatus bookHotelStatus;
	
	private String userId;
	private String username;
		
	private String hotelId;
	private String email;
}
