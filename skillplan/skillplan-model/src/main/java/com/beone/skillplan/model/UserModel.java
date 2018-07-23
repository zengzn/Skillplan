package com.beone.skillplan.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserModel implements Serializable {
	private static final long serialVersionUID = -9052780503686903004L;
	private Long id;
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
