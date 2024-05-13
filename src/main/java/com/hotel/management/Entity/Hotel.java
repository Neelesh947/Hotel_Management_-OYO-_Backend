package com.hotel.management.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.management.Dto.hotelDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
public class Hotel {

	@Id
	private String hotelId;
	private String name;
	private Long price;
	@Lob
	private String description;
	
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] img;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="category_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	public hotelDto getDto() {
		hotelDto dto=new hotelDto();
		dto.setHotelId(hotelId);
		dto.setName(name);
		dto.setByteImg(img);
		dto.setPrice(price);
		dto.setDescription(description);
		dto.setCategoryId(category.getCategoryId());
		dto.setCategoryName(category.getName());
		dto.setUserId(user.getUserId());
		dto.setUsername(user.getUsername());
		return dto;
	}

}
