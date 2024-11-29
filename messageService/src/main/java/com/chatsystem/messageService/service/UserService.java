package com.chatsystem.messageService.service;

import com.chatsystem.messageService.entity.User;
import com.chatsystem.messageService.security.JwtUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
  public void createUser(List<User> userList) throws Exception {
    String jwt = JwtUtil.generateToken("user-service-a");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + jwt);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<List<User>> request = new HttpEntity<>(userList, headers);
    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8081/api/resource";
    try {
      restTemplate.postForObject(url, request, String.class);
    } catch (Exception e) {
      e.printStackTrace();
    }


  }
}
