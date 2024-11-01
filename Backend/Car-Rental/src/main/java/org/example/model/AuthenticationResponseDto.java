package org.example.model;


import lombok.Data;
import org.example.enums.UserRole;

@Data
public class AuthenticationResponseDto {
    private String jwt;
    private UserRole userRole;
    private Long userId;
}
