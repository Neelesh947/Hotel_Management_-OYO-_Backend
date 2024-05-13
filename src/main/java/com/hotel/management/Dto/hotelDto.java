package com.hotel.management.Dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class hotelDto {

	@Id
	private String hotelId;
	private String name;
	private Long price;
	private String description;
	
	private byte[] byteImg;
	private String categoryId;
	private String categoryName;
    private MultipartFile img;	
	private Long quantity;
	private String userId;
	private String username;
}
