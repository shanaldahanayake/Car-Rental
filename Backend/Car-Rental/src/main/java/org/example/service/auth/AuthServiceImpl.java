package org.example.service.auth;

import lombok.RequiredArgsConstructor;
import org.example.entity.UserEntity;
import org.example.enums.UserRole;
import org.example.model.SignupRequestDto;
import org.example.model.UserDto;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService {
    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignupRequestDto signupRequestDto) {
        UserEntity user=new UserEntity();
        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(signupRequestDto.getPassword());
        user.setUserRole(UserRole.CUSTOMESRS);
        UserEntity createdUser = userRepository.save(user);
        UserDto userDto=new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
