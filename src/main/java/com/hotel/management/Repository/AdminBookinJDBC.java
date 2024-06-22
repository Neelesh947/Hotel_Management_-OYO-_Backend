package com.hotel.management.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hotel.management.Entity.AdminBookingListDto;

@Repository
public class AdminBookinJDBC {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<AdminBookingListDto> findAllData(){
		String sql = "SELECT h.hotel_id, h.description, h.img, h.name, h.price, h.category_id, " +
	             "h.user_id AS hotel_admin_id, " +
	             "b.booking_id, b.book_hotel_status, b.days, b.from_date, b.price AS booking_price, " +
	             "b.to_date, b.user_id AS booking_user_id " +
	             "FROM hotel_management.hotel h " +
	             "JOIN hotel_management.bookahotel b ON h.hotel_id = b.hotel_id";

		
		RowMapper<AdminBookingListDto> mapper=new RowMapper<AdminBookingListDto>() {			
			public AdminBookingListDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdminBookingListDto dto = new AdminBookingListDto();
				dto.setHotelId(rs.getString("hotel_id"));
				dto.setDescription(rs.getString("description"));
				dto.setName(rs.getString("name"));
				dto.setCategoryId(rs.getString("category_id"));
				dto.setAdminId(rs.getString("hotel_admin_id"));
				dto.setToDate(rs.getDate("to_date"));
				dto.setFromDate(rs.getDate("from_date"));
				dto.setBookHotelStatus(rs.getString("book_hotel_status"));
				
				dto.setUserId(rs.getString("booking_user_id"));
				
				return dto;
			}
		};
		
		List<AdminBookingListDto> admin=jdbcTemplate.query(sql, mapper);
		return admin;
	}
	
}
