package com.pek.apps.users.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@ApiModel (description="All details about the locations.")
@Entity 
public class Location {
	@Id
	@GeneratedValue
	private long id;
	private Date date;
	private String longitude;
	private String latitude;
	private String altitude;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Device device;
	
	public Location() {
	}
	
	public Location(String longitude, String latitude, String altitude, Device device) {
		super();
		this.date = new Date();
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.device = device;
	}
	
	public long getId() {
		return id;
	}
	
	public Date getDate() {
		return date;
	}
	public String getLongitude() {
		return longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getAltitude() {
		return altitude;
	}
	
	public Device getDevice() {
		return device;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", date=" + date + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", altitude=" + altitude + ", device=" + device + "]";
	}

}
