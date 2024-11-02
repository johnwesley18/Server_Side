package com.ust.OrdersServiceProvider.dto;



import com.ust.OrdersServiceProvider.model.OrderServiceProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderServiceProviderDTO {
    private String id;
    private String uid;
    private String sid;
    private OrderServiceProvider.OrderStatus status;
}