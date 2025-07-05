package com.bikeshed.bikeshed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/health")
  public String getString() {
    return "Server is up";
  }
}
