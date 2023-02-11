package com.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "groups")
public class Groups {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name ="group_name")
	private String group_name;
	
	@OneToMany(targetEntity = GroupAuthorities.class, mappedBy = "groups", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<GroupAuthorities> groupAuthorities = new HashSet<>();
	

	@OneToMany(targetEntity = GroupMembers.class, mappedBy = "groups", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<GroupMembers> groupMembers = new HashSet<>();

	
	public Groups() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Set<GroupAuthorities> getGroupAuthorities() {
		return groupAuthorities;
	}

	public void setGroupAuthorities(Set<GroupAuthorities> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}

	public Set<GroupMembers> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Set<GroupMembers> groupMembers) {
		this.groupMembers = groupMembers;
	}


}
