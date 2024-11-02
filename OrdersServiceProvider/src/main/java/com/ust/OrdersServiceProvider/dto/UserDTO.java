package com.ust.OrdersServiceProvider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String uid;
    private String Username;
    private String email;
    private String password;
    private String address;
    private String contact;
    private String profileImg;
}