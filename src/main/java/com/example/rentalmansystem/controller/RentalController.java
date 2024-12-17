package com.example.rentalmansystem.controller;


import com.example.rentalmansystem.Entity.Rental;
import com.example.rentalmansystem.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public String getAllRentals(Model model) {
        List<Rental> rentals = rentalService.getAllRentals();
        model.addAttribute("rentals", rentals);
        return "list";
    }

    @PostMapping("/rent")
    public String rentObject(
            @RequestParam Long rentalObjectId,
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Principal principal
    ) {
        rentalService.rentObject(rentalObjectId, String.valueOf(userId), startDate, endDate);
        return "redirect:/rentals";
    }

    @GetMapping("/my-rentals")
    public String getUserRentals(Model model, Principal principal) {
        List<Rental> rentals = rentalService.getRentalsByUserEmail(principal.getName());
        model.addAttribute("rentals", rentals);
        return "rentals/my-rentals";
    }

    @PostMapping("/complete/{id}")
    public String completeRental(@PathVariable Long id) {
        rentalService.completeRental(id);
        return "redirect:/rentals";
    }
}

