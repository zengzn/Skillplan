package com.beone.skillplan.model;

import java.io.Serializable;
import java.util.List;

public class SkillModel implements Serializable {
	private static final long serialVersionUID = -9142008106594282121L;

	private Long id;
	private String name;
	private List<String> users;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getUsers() {
		return this.users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
}
