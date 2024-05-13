package com.hotel.management.ServicceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.management.Entity.Category;
import com.hotel.management.Repository.CategoryRepository;
import com.hotel.management.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category createCategory(Category category) {
		String uuid=UUID.randomUUID().toString();
		category.setCategoryId(uuid);
		return this.categoryRepository.save(category);
	}
	
	public Category getCategoryById(String id) {
		return this.categoryRepository.findById(id).orElse(null);
	}
	
	public List<Category> findAllCategory()
	{
		return categoryRepository.findAll();
	}
	
}
