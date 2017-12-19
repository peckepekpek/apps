package com.pek.apps.users.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel (description="All details about the user.")
@Entity 
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min=2, message="Name should have atleast 2 character")
	private String name;
	
	@ApiModelProperty(notes="Rol only can be 'admin' or 'user'")
	@Pattern(regexp="(admin|user)", message="Rol only can be 'admin' or 'user'")
	private String role;
	
	@OneToMany(mappedBy="user")
	private List<Device> devices;
	
	public List<Device> getDevices() {
		return devices;
	}

	protected User () {
		
	}
	
	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + "]";
	}

}
