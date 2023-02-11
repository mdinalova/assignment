package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {

	
}
