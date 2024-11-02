package com.ust.AdminService.repo;


import com.ust.AdminService.model.Admin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AdminRepository extends ReactiveMongoRepository<Admin, String> {
    Mono<Admin> findByEmailAndPassword(String email, String password);
}
