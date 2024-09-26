package ru.imakhnoo.toDo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.imakhnoo.toDo.service.UserService;


@Controller
@RequiredArgsConstructor
public class RegisterUserController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Вернуть страницу регистрации
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        try {
            userService.registerUser(name, username, password);
            return "redirect:/auth";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorRegister", e.getMessage());
            return "register";
        }
    }
}

