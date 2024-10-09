package org.example.service.auth;

import org.example.model.SignupRequestDto;
import org.example.model.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequestDto signupRequestDto);
    boolean hasCustomerWithEmail(String email);
}
