package com.ust.API_Gateway.service;


import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface MyReactiveUserDetailsService extends ReactiveUserDetailsService {
    @Override
    Mono<UserDetails> findByUsername(String username);
}
