package com.pek.apps.users.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pek.apps.users.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

}
