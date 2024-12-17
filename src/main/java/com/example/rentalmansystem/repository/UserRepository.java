package com.example.rentalmansystem.repository;



import com.example.rentalmansystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

