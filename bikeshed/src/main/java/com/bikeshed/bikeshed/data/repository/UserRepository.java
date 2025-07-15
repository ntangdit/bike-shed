package com.bikeshed.bikeshed.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikeshed.bikeshed.data.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
  Optional<User> findByNameIgnoreCase(String name);
}
