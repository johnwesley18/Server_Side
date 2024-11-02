package com.ust.OrdersServiceProvider.dto;


import lombok.Data;

@Data
public class ServiceProviderDTO {
    private String sid;
    private String email;
    private String password;
    private Integer rating;
    private String experience;
    private String city;
    private String description;
    private Integer serviceCost;
    private String profileImg;
    private String serviceType;
    private String working;
}
