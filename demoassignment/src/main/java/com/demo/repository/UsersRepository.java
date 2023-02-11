package com.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	@Modifying
	@Query("UPDATE Users u SET u.password=:password, u.enabled=:enabled WHERE u.username=:username")
	void updateUserPasswordEnable(String password, Boolean enabled, String username);
	
}
