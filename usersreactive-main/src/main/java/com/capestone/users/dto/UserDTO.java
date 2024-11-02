package com.capestone.users.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    @Id
    private String uid;

//    @NotBlank(message = "Username is required")
    private String username;

//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
//    @Indexed(unique = true)
    private String email;

//    @NotBlank(message = "Password is required")
    private String password;

    private String address;

//    @NotBlank(message = "Contact number is required")
//    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be 10 digits")
//    @Indexed(unique = true)
    private String contact;

    private String profileImg;
}