package com.chatsystem.user_service.controller;

import com.chatsystem.user_service.entity.User;
import com.chatsystem.user_service.service.KafkaProducerService;
import com.chatsystem.user_service.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final KafkaProducerService kafkaProducerService;
  private final UserService userService;

  public UserController(KafkaProducerService kafkaProducerService, UserService userService) {
    this.kafkaProducerService = kafkaProducerService;
    this.userService = userService;
  }

  @PostMapping("/filters")
  public User filter(@RequestBody String phoneNumber) {
    return userService.findUser(phoneNumber);
  }

  @PostMapping
  public String addUser(@RequestBody List<User> users) {
    for (User u : users) {
      kafkaProducerService.sendUserEvent("user-topic", u);
    }
    return "Added will add users";
  }
}
