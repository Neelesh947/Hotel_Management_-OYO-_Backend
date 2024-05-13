package com.hotel.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.management.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{

}
