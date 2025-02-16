package com.shop.altenshop.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "Firstname cannot be Empty")
    private String firstname;
    @NotEmpty(message = "username cannot be Empty")
    private String username;
    @NotEmpty(message = "Email cannot be Empty")
    @Email(message = "Email must be valid")
    private String email;
    @NotEmpty(message = "Passsword cannot be Empty")
    private String password;
}