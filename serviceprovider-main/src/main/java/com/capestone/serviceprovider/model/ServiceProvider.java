package com.capestone.serviceprovider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "service_providers")
    public class ServiceProvider {

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
    private ProviderStatus status;

    public enum ProviderStatus {
        ACCEPTED,
        PENDING
    }

    }
