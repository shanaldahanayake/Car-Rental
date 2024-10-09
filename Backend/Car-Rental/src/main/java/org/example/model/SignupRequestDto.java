package org.example.model;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String email;
    private String name;
    private String password;

}
