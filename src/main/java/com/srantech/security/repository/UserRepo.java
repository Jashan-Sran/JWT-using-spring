package com.srantech.security.repository;

import com.srantech.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> { // interface for connecting to DB

    Optional<User> findByEmail(String email);

}
