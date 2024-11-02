package com.capestone.serviceprovider.dto;

import com.capestone.serviceprovider.model.ServiceProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceProviderDTO {
    @Id
    private String sid;
    private String name;
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
    private ServiceProvider.ProviderStatus status;
    // Constructors, getters, and setters
}
