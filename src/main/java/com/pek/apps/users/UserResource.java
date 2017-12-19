package com.pek.apps.users;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pek.apps.users.entity.Device;
import com.pek.apps.users.entity.Location;
import com.pek.apps.users.entity.User;
import com.pek.apps.users.exceptions.DeviceNotFoundException;
import com.pek.apps.users.exceptions.UserNotFoundException;
import com.pek.apps.users.service.DeviceRepository;
import com.pek.apps.users.service.LocationRepository;
import com.pek.apps.users.service.UserRepository;

@RestController
public class UserResource {
	
	@Autowired
	private UserRepository u_repository;
	
	@Autowired
	private LocationRepository l_repository;
	
	@Autowired
	private DeviceRepository d_repository;
	
	@GetMapping ("/users")
	public List<User> retrieveAllUsers() {
		return u_repository.findAll();
	}
	
	@GetMapping ("/users/{id}")
	public Optional<User> retrieveUser (@PathVariable int id) {
		Optional<User> user = u_repository.findById((long) id);
		if (!user.isPresent()) 
			throw new UserNotFoundException("id -"+ id);
		return user;
		
	}
	
	@DeleteMapping ("/users/{id}")
	public void deleteUser (@PathVariable int id) {
		Optional<User> user = u_repository.findById((long) id);
		if (!user.isPresent()) {
			 throw new UserNotFoundException("id-"+ id);
		}
		u_repository.delete(user.get());
	}
	
	@PostMapping ("/users")
	public ResponseEntity<Object> createUser (@Valid @RequestBody User user) {
		User savedUser = u_repository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/users/{id}/devices")
	  public List<Device> retrieveAllUserDevices(@PathVariable int id) {
	    Optional<User> userOptional = u_repository.findById((long) id);
	    if(!userOptional.isPresent()) {
	      throw new UserNotFoundException("id-" + id);
	    }
	    return userOptional.get().getDevices();
	  }

	 @PostMapping("/users/{id}/devices")
	  public ResponseEntity<Object> createDevice(@PathVariable int id, @RequestBody Device device) {
	    
	    Optional<User> userOptional = u_repository.findById((long) id);
	    
	    if(!userOptional.isPresent()) {
	      throw new UserNotFoundException("id-" + id);
	    }
	    User user = userOptional.get(); 
	    device.setUser(user);
	    d_repository.save(device);
	    URI location_page = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(device.getId())
	        .toUri();

	    return ResponseEntity.created(location_page).build();

	  }
	 
		@GetMapping ("/devices")
		public List<Device> retrieveAllDevices() {
			return d_repository.findAll();
		}
	 
	 
	 @PostMapping("/devices/{id}/locations")
	  public ResponseEntity<Object> createLocation(@PathVariable String id, @RequestBody Location location) {
	    
	    Optional<Device> deviceOptional = d_repository.findById((String) id);
	    
	    if(!deviceOptional.isPresent()) {
	      throw new DeviceNotFoundException("id-" + id);
	    }
	    Device device = deviceOptional.get(); 
	    location.setDevice(device);
	    location.setDate(new Date());
	    l_repository.save(location);
	    URI location_page = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(device.getId())
	        .toUri();

	    return ResponseEntity.created(location_page).build();

	  }

	 @GetMapping("/devices/{id}")
	  public Optional<Device> retrievUserforDevice(@PathVariable String id) {
	    Optional<Device> deviceOptional = d_repository.findById((String) id);
	    if(!deviceOptional.isPresent()) {
	      throw new DeviceNotFoundException("id-" + id);
	    }
	    return deviceOptional;
	  }
}
