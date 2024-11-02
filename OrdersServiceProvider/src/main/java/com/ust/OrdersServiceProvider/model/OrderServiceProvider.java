package com.ust.OrdersServiceProvider.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "orders_service_provider")
public class OrderServiceProvider {
    @Id
    private String id;
    private String uid;
    private String sid;
    private OrderStatus status;

    public enum OrderStatus {
        IN_PROGRESS,
        COMPLETED
    }
}
