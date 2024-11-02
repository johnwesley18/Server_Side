package com.ust.OrdersServiceProvider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderServiceProviderWithDetailsDTO {
    private OrderServiceProviderDTO order;
    private ServiceProviderDTO serviceProvider;
}