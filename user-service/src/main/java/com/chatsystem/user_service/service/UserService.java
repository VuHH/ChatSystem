package com.chatsystem.user_service.service;

import com.chatsystem.user_service.entity.User;
import com.chatsystem.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final RedisService redisService;

  @Autowired
  public UserService(UserRepository userRepository, RedisService redisService) {
    this.userRepository = userRepository;
    this.redisService = redisService;
  }

  public void saveUser(User user) {
    User userFind = findUser(user.getPhoneNumber());
    if (userFind != null) {
      userRepository.save(user);
      redisService.saveUser(user.getPhoneNumber(), user, 3600);
    }
  }

  public User findUser(String phoneNumber) {
    if (redisService.isUserExist(phoneNumber)) {
      return redisService.getUser(phoneNumber);
    }
    return userRepository.findUserByPhoneNumber(phoneNumber);
  }
}
