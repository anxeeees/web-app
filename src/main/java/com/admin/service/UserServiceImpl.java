package com.admin.service;

import com.admin.dto.UserDto;
import com.admin.model.User;
import com.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getRole(), userDto.getFullName()); // values of constructor in class
        return userRepository.save(user); // returns the entity
    }
}
