package com.example.rentalmansystem.repository;


import com.example.rentalmansystem.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserEmail(String email);
}

