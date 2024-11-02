package com.capestone.serviceprovider.repository;

import com.capestone.serviceprovider.model.ServiceProvider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ServiceProviderRepository extends ReactiveMongoRepository<ServiceProvider, String> {
    Flux<ServiceProvider> findByCity(String city);
}