package com.example.jobtracker.repository;

import com.example.jobtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Integer> {
}
