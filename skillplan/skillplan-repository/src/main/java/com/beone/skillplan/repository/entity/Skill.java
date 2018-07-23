package com.beone.skillplan.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the skill database table.
 * 
 */
@Entity
@Table(name = "skill")
@NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
public class Skill extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;

	private List<User> skilledUsers;
	
	// bi-directional many-to-many association to Skill
	@ManyToMany
	@JoinTable(name = "user_skill", joinColumns = { @JoinColumn(name = "skill_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	// @JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
	public List<User> getSkilledUsers() {
		return this.skilledUsers;
	}

	public void setSkilledUsers(List<User> skilledUsers) {
		this.skilledUsers = skilledUsers;
	}

	
	@Column(nullable = false, length = 255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}