package com.beone.skillplan.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UserSkillModel implements Serializable {

	private static final long serialVersionUID = -1108743342111617790L;

	private Long id;
	
	
	private List<String> users;
	private List<String> skills;
	
	@Min(0)
	@Max(4)
	private int level;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<String> getUsers() {
		return this.users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	public List<String> getSkills() {
		return this.skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
}
