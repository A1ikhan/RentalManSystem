package com.example.rentalmansystem.service;

import com.example.rentalmansystem.Entity.Rental;
import com.example.rentalmansystem.Entity.RentalObject;
import com.example.rentalmansystem.Entity.User;
import com.example.rentalmansystem.repository.RentalRepository;
import com.example.rentalmansystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalObjectService rentalObjectService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public Rental rentObject(Long rentalObjectId, String userEmail, LocalDate startDate, LocalDate endDate) {
        RentalObject rentalObject = rentalObjectService.getRentalObjectById(rentalObjectId);
        User user = userRepository.findByEmail(userEmail);

        if (rentalObject != null && user != null && rentalObject.getStatus().equals("AVAILABLE")) {
            Rental rental = new Rental();
            rental.setRentalObject(rentalObject);
            rental.setUser(user);
            rental.setStartDate(startDate);
            rental.setEndDate(endDate);
            rental.setStatus("ACTIVE");

            // Обновляем статус объекта аренды
            rentalObject.setStatus("RENTED");
            rentalObjectService.saveRentalObject(rentalObject);

            // Отправляем email пользователю
            emailService.sendEmail(
                    user.getEmail(),
                    "Подтверждение аренды",
                    "Уважаемый " + user.getName() + ", вы успешно арендовали " +
                            rentalObject.getName() + " с " + startDate + " по " + endDate + "."
            );

            return rentalRepository.save(rental);
        }
        return null;
    }
    public List<Rental> getRentalsByUserEmail(String email) {
        return rentalRepository.findByUserEmail(email);
    }


    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public void completeRental(Long id) {
        Rental rental = rentalRepository.findById(id).get();
    }
}
