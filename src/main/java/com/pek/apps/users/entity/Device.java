package com.pek.apps.users.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@ApiModel (description="All details about the Device.")
@Entity 
public class Device {
	
	protected Device() {
	}
	@Id
	private String id;
	
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	
	@OneToMany(mappedBy="device")
	private List<Location> locations;
	
	public Device(String id, User user, List<Location> locations) {
		super();
		this.id = id;
		this.user = user;
		this.locations = locations;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
	@Override
	public String toString() {
		return "Device [id=" + id + ", user=" + user + ", locations=" + locations + "]";
	}
	
	

}
