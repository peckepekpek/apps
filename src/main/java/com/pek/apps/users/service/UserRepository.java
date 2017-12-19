package com.pek.apps.users.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pek.apps.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
