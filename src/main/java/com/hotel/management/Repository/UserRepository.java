package com.hotel.management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.management.Entity.User;
import com.hotel.management.Enums.UserRoles;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findFirstByUsername(String username);
	
	public User findByUserRoles(UserRoles userRoles);
	
	User findByUsername(String username);
}
