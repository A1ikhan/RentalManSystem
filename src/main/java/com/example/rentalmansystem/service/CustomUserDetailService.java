package com.example.rentalmansystem.service;

import com.example.rentalmansystem.Entity.User;
import com.example.rentalmansystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с email " + email + " не найден");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),          // Используем email вместо username
                user.getPassword(),       // Зашифрованный пароль
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
    }
}

