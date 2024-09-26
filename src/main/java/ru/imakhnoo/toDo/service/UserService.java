package ru.imakhnoo.toDo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.imakhnoo.toDo.entity.MyUser;
import ru.imakhnoo.toDo.repository.UserRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(String name, String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Такой логин уже существует.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        MyUser myUser = new MyUser(name, username, encodedPassword);
        userRepository.save(myUser);
    }

    public MyUser findByUsername(String userName) {
        return this.userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName + " not found"));
    }
}

