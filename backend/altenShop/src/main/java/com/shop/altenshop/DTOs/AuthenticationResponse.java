package com.shop.altenshop.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.altenshop.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private Long id;
    private String firstname;
    private String username;
    private String email;
    private Role role;
}