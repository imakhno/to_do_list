package ru.imakhnoo.toDo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthPageController {
    @GetMapping
    public String getAuthPage() {
        return "auth";
    }
}
