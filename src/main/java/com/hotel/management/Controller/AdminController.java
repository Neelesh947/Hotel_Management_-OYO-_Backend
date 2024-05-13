package com.hotel.management.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.management.Dto.hotelDto;
import com.hotel.management.Entity.Category;
import com.hotel.management.Entity.Hotel;
import com.hotel.management.Service.CategoryService;
import com.hotel.management.Service.HotelService;

@RestController
@RequestMapping("/admin/")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/create-category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category)
	{
		Category cat=this.categoryService.createCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(cat);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllCategory()
	{
		List<Category> cat=this.categoryService.findAllCategory();
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable String id)
	{
		Category cat=this.categoryService.getCategoryById(id);
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}
	
	@PostMapping("/create-hotel")
	public ResponseEntity<hotelDto> createProduct(@ModelAttribute hotelDto dto) throws IOException
	{
		hotelDto productDto1=this.hotelService.addHotel(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
	}
	
	@GetMapping("/hotel-list")
	public ResponseEntity<List<Hotel>> getAllHotel()
	{
		List<Hotel> dto=this.hotelService.getAllHotelWithUserId();
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}	
}
