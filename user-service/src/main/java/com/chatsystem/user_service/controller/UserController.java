package com.chatsystem.user_service.controller;

import com.chatsystem.user_service.entity.User;
import com.chatsystem.user_service.service.KafkaProducerService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final KafkaProducerService kafkaProducerService;

  public UserController(KafkaProducerService kafkaProducerService) {
    this.kafkaProducerService = kafkaProducerService;
  }

  //  @PostMapping("/filters")
  //  public String filter(@RequestBody String username) {
  //    //return jwtUtil.generateToken(username);
  //  }

  @PostMapping
  public String addUser(@RequestBody List<User> users) {
    for (User u : users) {
      kafkaProducerService.sendUserEvent("user-topic", u);
    }
    return "Added users";
  }
}
