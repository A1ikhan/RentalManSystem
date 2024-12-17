package com.example.rentalmansystem.service;

import com.example.rentalmansystem.Entity.RentalObject;
import com.example.rentalmansystem.repository.RentalObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalObjectService {

    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    public List<RentalObject> getAllRentalObjects() {
        return rentalObjectRepository.findAll();
    }

    public RentalObject getRentalObjectById(Long id) {
        return rentalObjectRepository.findById(id).orElse(null);
    }

    public RentalObject saveRentalObject(RentalObject rentalObject) {
        return rentalObjectRepository.save(rentalObject);
    }

    public void deleteRentalObject(Long id) {
        rentalObjectRepository.deleteById(id);
    }
}
