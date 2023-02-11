package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.GroupAuthorities;

@Repository
public interface GroupAuthoritiesRepository extends JpaRepository<GroupAuthorities, Integer> {

	
}
