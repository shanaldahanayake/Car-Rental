package org.example.model;

import lombok.Data;
import org.example.enums.UserRole;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
