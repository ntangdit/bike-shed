package com.bikeshed.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikeshed.data.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
  User findByNameIgnoreCase(String name);
}
