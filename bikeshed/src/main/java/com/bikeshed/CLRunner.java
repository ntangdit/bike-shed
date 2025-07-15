package com.bikeshed;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bikeshed.data.entity.User;
import com.bikeshed.data.repository.UserRepository;

@Component
public class CLRunner implements CommandLineRunner {

  private final UserRepository userRepository;

  public CLRunner(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Optional<User> user = this.userRepository.findByNameIgnoreCase("alice");
    System.out.println(user);
  }
}
