package com.example.rentalmansystem.controller;

import com.example.rentalmansystem.Entity.RentalObject;
import com.example.rentalmansystem.service.RentalObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rental-objects")
public class RentalObjectController {

    @Autowired
    private RentalObjectService rentalObjectService;

    @GetMapping
    public String getAllRentalObjects(Model model) {
        List<RentalObject> rentalObjects = rentalObjectService.getAllRentalObjects();
        model.addAttribute("rentalObjects", rentalObjects);
        return "rental-objects/list";
    }

    @GetMapping("/{id}")
    public String getRentalObjectDetails(@PathVariable Long id, Model model) {
        RentalObject rentalObject = rentalObjectService.getRentalObjectById(id);
        model.addAttribute("rentalObject", rentalObject);
        return "rental-objects/details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("rentalObject", new RentalObject());
        return "rental-objects/create";
    }

    @PostMapping
    public String saveRentalObject(@ModelAttribute RentalObject rentalObject) {
        rentalObjectService.saveRentalObject(rentalObject);
        return "redirect:/rental-objects";
    }

    @GetMapping("/delete/{id}")
    public String deleteRentalObject(@PathVariable Long id) {
        rentalObjectService.deleteRentalObject(id);
        return "redirect:/rental-objects";
    }
}

