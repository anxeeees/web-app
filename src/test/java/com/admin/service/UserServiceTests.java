package com.admin.service;

import com.admin.dto.UserDto;
import com.admin.model.User;
import com.admin.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.admin.dto.UserDto;
import com.admin.model.User;
import com.admin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void UserService_CreateUser_ReturnsUser() {
        UserDto dto = UserDto.builder()
                .email("admin@admin.cz")
                .password("123")
                .role("ADMIN")
                .fullName("John Wick")
                .build();

        when(passwordEncoder.encode("123")).thenReturn("ENC(123)");

        User saved = User.builder()
                .email("admin@admin.cz")
                .password("ENC(123)")
                .role("ADMIN")
                .fullName("John Wick")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(saved);

        User savedUser = userService.save(dto);

        assertNotNull(savedUser);
        assertEquals("ENC(123)", savedUser.getPassword());

        verify(passwordEncoder).encode("123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void UserService_GetAllUser_ReturnsResponseData() {
        User user1 = User.builder()
                .email("admin@admin.cz")
                .password("ENC(123)")
                .role("ADMIN")
                .fullName("John Wick")
                .build();

        User user2 = User.builder()
                .email("user@user.cz")
                .password("ENC(456)")
                .role("USER")
                .fullName("Jane Doe")
                .build();

        List<User> users = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("admin@admin.cz", result.get(0).getEmail());
        assertEquals("user@user.cz", result.get(1).getEmail());

        verify(userRepository, times(1)).findAll();
    }
}

