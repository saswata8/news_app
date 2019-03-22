package com.stackroute.userservice.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User 
{
	@Id
	@NotBlank
	private String userId;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String password;
	
	@CreationTimestamp
	private Date created;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
