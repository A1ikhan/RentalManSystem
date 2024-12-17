package com.example.rentalmansystem.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rental_object_id", nullable = false)
    private RentalObject rentalObject;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // ACTIVE or COMPLETED

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public RentalObject getRentalObject() {
        return rentalObject;
    }
    public void setRentalObject(RentalObject rentalObject) {
        this.rentalObject = rentalObject;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

