package com.example.rentalmansystem.service;


import com.example.rentalmansystem.Entity.User;
import com.example.rentalmansystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // Получить список всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Найти пользователя по ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Сохранить нового пользователя или обновить существующего
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Удалить пользователя по ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User registerUser(User user) {
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>()); // Инициализируем roles, если оно null
        }
        user.getRoles().add("ROLE_USER"); // Назначаем роль по умолчанию
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифруем пароль
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
