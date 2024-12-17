package com.example.rentalmansystem.repository;



import com.example.rentalmansystem.Entity.RentalObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalObjectRepository extends JpaRepository<RentalObject, Long> {
}

