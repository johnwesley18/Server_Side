package com.ust.AdminService.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
