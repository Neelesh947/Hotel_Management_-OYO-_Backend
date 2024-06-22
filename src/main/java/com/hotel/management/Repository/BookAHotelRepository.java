package com.hotel.management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.management.Entity.BookAHotel;

@Repository
public interface BookAHotelRepository extends JpaRepository<BookAHotel,String>{

	//List<BookAHotel> findAllByHotelId(String hotelId);
	
	List<BookAHotel> findAllByUserUserId(String userId);
}
