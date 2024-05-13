package com.hotel.management.Service;

import java.util.List;

import com.hotel.management.Entity.Category;

public interface CategoryService {

	public Category createCategory(Category category);
	
	public Category getCategoryById(String id);
	
	public List<Category> findAllCategory();
}
