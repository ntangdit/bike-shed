package com.bikeshed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikeshed.data.entity.User;
import com.bikeshed.data.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class Controller {

  private final UserRepository userRepository;

  public Controller(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public User getUser(@RequestParam("name") String name) {
    return this.userRepository.findByNameIgnoreCase(name);
  }
}
