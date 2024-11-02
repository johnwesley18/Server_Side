package com.ust.OrdersServiceProvider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithDetailsDTO {
    private OrderServiceProviderDTO order;
    private UserDTO userdetails;
}
