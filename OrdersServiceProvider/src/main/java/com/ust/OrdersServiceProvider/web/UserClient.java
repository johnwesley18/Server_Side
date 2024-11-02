package com.ust.OrdersServiceProvider.web;

import com.ust.OrdersServiceProvider.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClient {
    private final WebClient webClient;

    public UserClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8087").build();

    }

    public Mono<UserDTO> getServiceProviderById(String uid) {
        return webClient.get()
                .uri("/user/get/{uid}", uid)
                .retrieve()
                .bodyToMono(UserDTO.class);
    }




}