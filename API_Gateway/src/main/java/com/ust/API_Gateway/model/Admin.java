package com.ust.API_Gateway.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "admins")
public class Admin {
    @Id
    private String aid;
    private String name;
    private String email;
    private String password;
}
