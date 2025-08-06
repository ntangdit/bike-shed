package com.bikeshed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikeshed.data.entity.User;
import com.bikeshed.data.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public User getUser(@RequestParam("name") String name) {
    return this.userRepository.findByNameIgnoreCase(name);
  }
}
