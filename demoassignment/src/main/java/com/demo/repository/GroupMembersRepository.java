package com.demo.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.GroupMembers;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMembers, Integer> {

	Optional<GroupMembers> findByusername(String username);
	
	
}
