package com.ust.API_Gateway.repo;


import com.ust.API_Gateway.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRoleRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
}
