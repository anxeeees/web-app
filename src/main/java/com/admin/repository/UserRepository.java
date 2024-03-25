package com.admin.repository;

import com.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository from the table User
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
