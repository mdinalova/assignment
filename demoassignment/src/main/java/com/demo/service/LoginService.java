package com.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.GroupAuthorities;
import com.demo.model.GroupMembers;
import com.demo.model.Groups;
import com.demo.model.Users;
import com.demo.repository.GroupAuthoritiesRepository;
import com.demo.repository.GroupMembersRepository;
import com.demo.repository.GroupsRepository;
import com.demo.repository.UsersRepository;

@Service
public class LoginService {
	
	@Autowired
	GroupMembersRepository groupMembersRepository;
	
	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	GroupsRepository groupsRepository;
	
	@Autowired
	GroupAuthoritiesRepository groupAuthoritiesRepository;
	
	// Group Members //
	
	public List<GroupMembers> getAllGroupMembers() {
		return groupMembersRepository.findAll();
	}
		
	public void save(GroupMembers groupMembers) {
		
		groupMembersRepository.save(groupMembers);
	}

	public GroupMembers createGroupMembers(GroupMembers groupMembers) {
		return groupMembersRepository.save(groupMembers);
	}

	public Optional<GroupMembers> getByusername (String username) {
		return groupMembersRepository.findByusername(username);
	}
	
	// Users //
	
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<Users> findByusername (String username) {
		return userRepository.findById(username);
	}
	

	public Users createUsers(Users users) {
		
		return userRepository.save(users); 
	}
	
	@Transactional
	public void updateUserPasswordEnable(String password, Boolean enabled, String username) {
		
		userRepository.updateUserPasswordEnable(password, enabled, username);
	}
	
	// Groups //
	
	public List<Groups> getAllGroups() {
		return groupsRepository.findAll();
	}
	
	public Optional<Groups> getGroups(Integer id) {
		return groupsRepository.findById(id);
	}
	
	// Authorities //
	
	public List<GroupAuthorities> getAllGroupAuthorities() {
		return groupAuthoritiesRepository.findAll();
	}
	
	public Optional<GroupAuthorities> getGroupAuthorities(Integer id) {
		return groupAuthoritiesRepository.findById(id);
	}

}
