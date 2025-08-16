package com.example.board2.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequest {
    @Email @NotBlank private String email;
    @NotBlank private String password;
    @NotBlank private String name;
}

