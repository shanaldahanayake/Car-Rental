package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.SignupRequestDto;
import org.example.model.UserDto;
import org.example.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.hasCustomerWithEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Customer already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto=authService.createCustomer(signupRequestDto);
        if(userDto==null){
            return new ResponseEntity<>("Customer Not Created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

}
