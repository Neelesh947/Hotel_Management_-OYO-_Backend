package com.hotel.management.Entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminBookingListDto {

	private String hotelId;
	private String name;
	private String description;
	private String categoryId;
	private String category;
	private String adminId;
	private String adminUsername;
	
	private String bookingId;
	private String bookHotelStatus;
	private Date toDate;
	private Date fromDate;
	private Long days;
	private Long price;
	private String userId;
	private String username;	
}
