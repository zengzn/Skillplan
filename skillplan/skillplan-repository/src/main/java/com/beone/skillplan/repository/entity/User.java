package com.beone.skillplan.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	
	private List<Skill> userSkills;
	
    // bi-directional many-to-many association to Skill
	@ManyToMany
	@JoinTable(name = "user_skill", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "skill_id") })
	// @JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
	public List<Skill> getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(List<Skill> userSkills) {
		this.userSkills = userSkills;
	}

	
	@Column(name = "first_name", length = 45)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 45)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}