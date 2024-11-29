package com.chatsystem.user_service.service;

import com.chatsystem.user_service.entity.User;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
  private final RedisTemplate<String, Object> redisTemplate;

  public RedisService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public boolean isUserExist(String phoneNumber) {
    return redisTemplate.hasKey(phoneNumber);
  }

  public void saveUser(String phoneNumber, User userInfo, long durationInSeconds) {
    redisTemplate.opsForValue().set(phoneNumber, userInfo, durationInSeconds, TimeUnit.SECONDS);
  }

  public User getUser(String phoneNumber) {
    return (User) redisTemplate.opsForValue().get(phoneNumber);
  }
}
