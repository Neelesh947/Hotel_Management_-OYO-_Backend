package com.hotel.management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.management.Entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String>{

	List<Hotel> findByUserUserId(String userId);
}
