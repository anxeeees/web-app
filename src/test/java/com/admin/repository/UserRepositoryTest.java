package com.admin.repository;

import com.admin.model.User;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // Always void
    @Test
    public void UserRepository_SaveAll_ReturnSavedUser() {
        // Arrange
        User user = User.builder()
                .fullName("John Wick")
                .role("ADMIN")
                .email("admin@admin.cz")
                .password("123").build();

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void UserRepository_GetAll_ReturnMoreThenOneUser() {
        User user1 = User.builder()
                .fullName("John Wick")
                .role("ADMIN")
                .email("admin@admin.cz")
                .password("123").build();
        User user2 = User.builder()
                .fullName("John Wick")
                .role("ADMIN")
                .email("admin2@admin.cz")
                .password("123").build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        Assertions.assertNotNull(userList);
        Assertions.assertTrue(userList.size() == 2);
    }

    @Test
    public void UserRepository_FindById_ReturnsUser() {
        User user = User.builder()
                .fullName("John Wick")
                .role("ADMIN")
                .email("admin@admin.cz")
                .password("123").build();

        userRepository.save(user);

        User userList = userRepository.findById(user.getId()).get();

        Assertions.assertNotNull(userList);
    }

    @Test
    public void UserRepository_FindByEmail_ReturnUserNotNull() {
        User user = User.builder()
                .fullName("John Wick")
                .role("ADMIN")
                .email("admin@admin.cz")
                .password("123").build();

        userRepository.save(user);

        User userList = userRepository.findByEmail(user.getEmail());

        Assertions.assertNotNull(userList);
    }

    @Test
    public void UserRepository_UpdateUser_ReturnUserNotNull() {
        User user = User.builder()
                .fullName("John Wick")
                .role("ADMIN")
                .email("admin@admin.cz")
                .password("123").build();

        userRepository.save(user);

        User userSave = userRepository.findById(user.getId()).get();
        userSave.setFullName("John Wick");
        userSave.setRole("ADMIN");

        User updatedUser = userRepository.save(userSave);


        Assertions.assertNotNull(updatedUser.getEmail());
        Assertions.assertNotNull(updatedUser.getRole());
    }

    @Test
    public void UserRepository_UserDelete_ReturnUserIsEmpty() {
        User user = User.builder()
                .fullName("John Wick")
                .role("ADMIN")
                .email("admin@admin.cz")
                .password("123").build();

        userRepository.save(user);

        userRepository.deleteById(user.getId());
        Optional<User> userReturn = userRepository.findById(user.getId());

        Assertions.assertTrue(userReturn.isEmpty());
    }

}
