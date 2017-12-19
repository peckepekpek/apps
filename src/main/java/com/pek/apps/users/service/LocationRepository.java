package com.pek.apps.users.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pek.apps.users.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
