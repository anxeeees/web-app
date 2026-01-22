package com.admin.service;

import com.admin.dto.UserDto;
import com.admin.model.User;
import com.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .fullName(userDto.getFullName())
                .role(userDto.getRole())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        return userRepository.save(user);
    }
}
