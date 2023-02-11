package com.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.GroupAuthorities;
import com.demo.model.GroupMembers;
import com.demo.model.Groups;
import com.demo.model.Users;
import com.demo.service.LoginService;



@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginController{

	@Autowired
	private LoginService loginService;

	@Bean("passwordEncoder")
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
			
	@GetMapping(path = "/users/list")
	public List<Users> getUsers(){
                        
		return loginService.getAllUsers();
	}
		
	@GetMapping(path = "/users/groupmembers")
	public List<GroupMembers> getGroupMembers(){
		
		return loginService.getAllGroupMembers();
	}

	@GetMapping(path = "/users/groupsall")
	public List<Groups> getAllGroups() {
            
		return loginService.getAllGroups();
	}

	@PostMapping(path = "/users/creategroupmembers")
	public ResponseEntity<GroupMembers> createGroupMembers(@RequestParam String username, @RequestParam String password, @RequestParam Integer groupid) {
		
		GroupMembers groupMembers = new GroupMembers();
		
		groupMembers.setUsername(username);
		
		Optional<Groups> groups = loginService.getGroups(groupid);
		Groups groups2 = groups.get();
		groupMembers.setGroups(groups2);
		
		
		Optional<GroupAuthorities> groupAuthorities = loginService.getGroupAuthorities(groupid);
		GroupAuthorities groupAuthorities2 = groupAuthorities.get();
		groupMembers.setGroupAuthorities(groupAuthorities2);
		
		
		Users users = new Users();
		
		users.setUsername(groupMembers.getUsername());
		users.setPassword(passwordEncoder().encode(password));
		users.setEnabled(true);
		
		loginService.createUsers(users);
		
		loginService.save(groupMembers);
                
    	return new ResponseEntity<GroupMembers>(groupMembers, HttpStatus.CREATED);
	}		

}
