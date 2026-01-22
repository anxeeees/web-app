package com.admin.controller;

import com.admin.dto.UserDto;
import com.admin.service.CustomUserDetail;
import com.admin.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService  userService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void getRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void getLogin_returnsLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void postRegistration_callsSave_andReturnsRegisterWithMessage() throws Exception {
        mockMvc.perform(post("/registration")
                        .param("email", "admin@admin.cz")
                        .param("password", "123")
                        .param("role", "ADMIN")
                        .param("fullName", "John Wick"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attribute("message", "Registered Successfuly!"));

        verify(userService, times(1)).save(any(UserDto.class));
    }

    @Test
    void adminPage_loadsUserDetails_andReturnsAdminView() throws Exception {
        Principal principal = () -> "admin@admin.cz";

        CustomUserDetail userDetails = mock(CustomUserDetail.class);
        when(userDetails.getFullName()).thenReturn("John Wick");

        when(userDetailsService.loadUserByUsername("admin@admin.cz"))
                .thenReturn(userDetails);

        mockMvc.perform(get("/admin-page").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attribute("user", userDetails));

        verify(userDetailsService).loadUserByUsername("admin@admin.cz");
    }

}
