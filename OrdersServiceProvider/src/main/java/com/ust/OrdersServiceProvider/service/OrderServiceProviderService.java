package com.ust.OrdersServiceProvider.service;


import com.ust.OrdersServiceProvider.dto.OrderServiceProviderDTO;
import com.ust.OrdersServiceProvider.dto.OrderServiceProviderWithDetailsDTO;
import com.ust.OrdersServiceProvider.dto.UserWithDetailsDTO;
import com.ust.OrdersServiceProvider.web.ServiceProviderClient;
import com.ust.OrdersServiceProvider.web.UserClient;
import com.ust.OrdersServiceProvider.model.OrderServiceProvider;
import com.ust.OrdersServiceProvider.repo.OrderServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.ust.OrdersServiceProvider.model.OrderServiceProvider.OrderStatus;


@Service
public class OrderServiceProviderService {
    @Autowired
    private OrderServiceProviderRepository orderServiceProviderRepository;


    @Autowired
    private ServiceProviderClient serviceProviderClient;

    @Autowired
    private UserClient userClient;


    public Mono<OrderServiceProviderDTO> createOrder(String uid, String sid) {
        OrderServiceProvider order = new OrderServiceProvider();
        order.setUid(uid);
        order.setSid(sid);
        order.setStatus(OrderServiceProvider.OrderStatus.IN_PROGRESS);
        return orderServiceProviderRepository.save(order).map(this::convertToDTO);
    }

    public Flux<OrderServiceProviderDTO> getAllOrders() {
        return orderServiceProviderRepository.findAll().map(this::convertToDTO);
    }

    public Flux<OrderServiceProviderDTO> getOrdersByStatus(OrderStatus status) {
        return orderServiceProviderRepository.findByStatus(status).map(this::convertToDTO);
    }

    public Mono<OrderServiceProviderDTO> updateOrderStatus(String uid, String sid, OrderStatus status) {
        return orderServiceProviderRepository.findAll()
                .filter(order -> order.getUid().equals(uid) && order.getSid().equals(sid))
                .next()
                .flatMap(order -> {
                    order.setStatus(status);
                    return orderServiceProviderRepository.save(order);
                })
                .map(this::convertToDTO);
    }

    public Flux<OrderServiceProviderDTO> getOrderDetailsBySid(String sid) {
        return orderServiceProviderRepository.findBySid(sid).map(this::convertToDTO);
    }

    private OrderServiceProviderDTO convertToDTO(OrderServiceProvider entity) {
        OrderServiceProviderDTO dto = new OrderServiceProviderDTO();
        dto.setId(entity.getId());
        dto.setUid(entity.getUid());
        dto.setSid(entity.getSid());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public Flux<OrderServiceProviderWithDetailsDTO> getOrdersWithServiceProviderDetails() {
        return orderServiceProviderRepository.findAll()
                .flatMap(order -> {
                    return serviceProviderClient.getServiceProviderById(order.getSid())
                            .map(serviceProvider -> new OrderServiceProviderWithDetailsDTO(convertToDTO(order), serviceProvider));
                });
    }


    public Flux<UserWithDetailsDTO> getOrdersWithUserDetails() {
        return orderServiceProviderRepository.findAll()
                .flatMap(order -> {
                    return userClient.getServiceProviderById(order.getUid())
                            .map(serviceProvider -> new UserWithDetailsDTO(convertToDTO(order), serviceProvider));
                });
    }

    public Flux<OrderServiceProviderDTO> getOrdersByUserId(String uid) {
        return orderServiceProviderRepository.findAll().filter(order -> order.getUid().equals(uid)).map(this::convertToDTO);
    }
}
