package com.hotel.management.Entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.management.Dto.BookAHotelDto;
import com.hotel.management.Enums.BookHotelStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAHotel {

	@Id
	private String bookingId;
	private Date toDate;
	private Date fromDate;
	private Long days;
	private Long price;
	
	@Enumerated(EnumType.STRING)
	private BookHotelStatus bookHotelStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="hotel_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Hotel hotel;
	
	public BookAHotelDto bookAHotelDto() {
		BookAHotelDto bookAHotelDto=new BookAHotelDto();
		bookAHotelDto.setBookingId(bookingId);
		bookAHotelDto.setDays(days);
		bookAHotelDto.setBookHotelStatus(bookHotelStatus);
		bookAHotelDto.setPrice(price);
		bookAHotelDto.setFromDate(fromDate);
		bookAHotelDto.setToDate(toDate);
		bookAHotelDto.setUserId(user.getUserId());
		bookAHotelDto.setUsername(user.getUsername());
		bookAHotelDto.setEmail(user.getEmail());
		bookAHotelDto.setHotelId(hotel.getHotelId());
		return bookAHotelDto;
	}
}
