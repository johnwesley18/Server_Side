package com.ust.OrdersServiceProvider.repo;


import com.ust.OrdersServiceProvider.model.OrderServiceProvider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderServiceProviderRepository extends ReactiveMongoRepository<OrderServiceProvider, String> {
    Flux<OrderServiceProvider> findByStatus(OrderServiceProvider.OrderStatus status);
    Flux<OrderServiceProvider> findBySid(String sid);
}
