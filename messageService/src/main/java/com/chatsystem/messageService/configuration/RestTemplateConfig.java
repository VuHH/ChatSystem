package com.chatsystem.messageService.configuration;

import com.chatsystem.messageService.security.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {
  @Bean
  public RestTemplate restTemplate(TokenInterceptor tokenInterceptor) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setInterceptors(Collections.singletonList(tokenInterceptor));
    return restTemplate;
  }
}
