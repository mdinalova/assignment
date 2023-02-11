package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "group_members")
public class GroupMembers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "username")
	private String username;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Groups groups;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorities_id")
	private GroupAuthorities groupAuthorities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonIgnore
	public Groups getGroups() {
		return groups;
	}

	@JsonIgnore
	public void setGroups(Groups groups) {
		this.groups = groups;
	}
	
	@JsonIgnore
	public GroupAuthorities getGroupAuthorities() {
		return groupAuthorities;
	}
	
	@JsonIgnore
	public void setGroupAuthorities(GroupAuthorities groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}

}

