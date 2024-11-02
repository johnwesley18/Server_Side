package com.ust.OrdersServiceProvider.controller;


import com.ust.OrdersServiceProvider.dto.OrderServiceProviderDTO;
import com.ust.OrdersServiceProvider.dto.OrderServiceProviderWithDetailsDTO;
import com.ust.OrdersServiceProvider.dto.UserWithDetailsDTO;
import com.ust.OrdersServiceProvider.model.OrderServiceProvider;
import com.ust.OrdersServiceProvider.service.OrderServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderServiceProviderController {

    @Autowired
    private OrderServiceProviderService orderServiceProviderService;

    @PostMapping("/postorder/{uid}/{sid}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OrderServiceProviderDTO> createOrder(@PathVariable String uid, @PathVariable String sid) {
        return orderServiceProviderService.createOrder(uid, sid);
    }

    @GetMapping("/getorders")
    public Flux<OrderServiceProviderDTO> getAllOrders() {
        return orderServiceProviderService.getAllOrders();
    }

    @GetMapping("/getordersbystatus/{status}")
    public Flux<OrderServiceProviderDTO> getOrdersByStatus(@PathVariable OrderServiceProvider.OrderStatus status) {
        return orderServiceProviderService.getOrdersByStatus(status);
    }

    @PutMapping("/updatestatusoforder/{uid}/{sid}/{status}")
    public Mono<OrderServiceProviderDTO> updateOrderStatus(
            @PathVariable String uid,
            @PathVariable String sid,
            @PathVariable OrderServiceProvider.OrderStatus status) {
        return orderServiceProviderService.updateOrderStatus(uid, sid, status);
    }

    // THIS IS NOT CORRECT WE HAVE TO CONNECT FEIGN CLIENT FOR THIS END POINT TO FETCH ENTIRE SERVICE_PROVIDER DETAILS ALONG WITH ORDER DETAILS
//    @GetMapping("/sp/getorderdetailsbysid/{sid}")
//    public Flux<OrderServiceProviderDTO> getOrderDetailsBySid(@PathVariable String sid) {
//        return orderServiceProviderService.getOrderDetailsBySid(sid);
//    }

    @GetMapping("/with-provider-details")
    public Flux<OrderServiceProviderWithDetailsDTO> getOrdersWithServiceProviderDetails() {
        return orderServiceProviderService.getOrdersWithServiceProviderDetails();
    }

    @GetMapping("/with-user-details")
    public Flux<UserWithDetailsDTO> getOrdersWithUserDetails() {
        return orderServiceProviderService.getOrdersWithUserDetails();
    }
    @GetMapping("/with/{uid}")
    public Flux<OrderServiceProviderDTO> getOrdersByUserId(@PathVariable String uid) {
        return orderServiceProviderService.getOrdersByUserId(uid);
    }
    @GetMapping("with/{sid}")
    public Flux<OrderServiceProviderDTO> getOrdersBySid(@PathVariable String sid) {
        return orderServiceProviderService.getOrderDetailsBySid(sid);
    }
}