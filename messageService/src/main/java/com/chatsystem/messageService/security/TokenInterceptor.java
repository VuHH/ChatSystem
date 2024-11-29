package com.chatsystem.messageService.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenInterceptor implements ClientHttpRequestInterceptor {
  private final RestTemplate restTemplate;

  @Value("${auth.address}")
  private String authAddress;

  @Value("${auth.username}")
  private String username;

  @Value("${auth.password}")
  private String password;

  public TokenInterceptor(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    String token = getJwtTokenFromAuthService();
    request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    return execution.execute(request, body);
  }

  private String getJwtTokenFromAuthService() {
    return restTemplate.postForObject(
        "http://" + authAddress + "/auth/login?username=" + username + "&password=" + password,
        null,
        String.class);
  }
}
