package com.tmdb.api.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Value("${tmdb.api.key}")
  private String apiKey;


  @Bean
  public RequestInterceptor requestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + apiKey);
      }
    };
  }
}
