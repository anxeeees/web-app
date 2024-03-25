package com.admin.service;

import com.admin.dto.UserDto;
import com.admin.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User save(UserDto userDto); // to save user information to user class
}
