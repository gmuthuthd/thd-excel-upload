package com.homedepot.excel.upload.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // This tells Hibernate to make a table out of this class
public class User {

	@Id
	@NotNull(message = "Id cannot be null")
	@Size(max = 15, message = "Id must be between 10 and 45 characters")
    private String id;
        
	@NotNull(message = "Name cannot be null")
	@Size(max = 45, message = "Name must be between 10 and 45 characters")
	private String name;
	
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
    private String email;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}

