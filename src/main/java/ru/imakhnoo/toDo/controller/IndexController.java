package ru.imakhnoo.toDo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.imakhnoo.toDo.components.UserAuthCheck;
import ru.imakhnoo.toDo.entity.MyUser;
import ru.imakhnoo.toDo.entity.Task;
import ru.imakhnoo.toDo.service.TaskService;
import ru.imakhnoo.toDo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
    private final UserAuthCheck userAuthCheck;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping
    public String getIndexPage(Model model, Principal principal) {
        MyUser myUser = this.userService.findByUsername(principal.getName());
        List<Task> taskList = this.taskService.findAllByUserId(Math.toIntExact(myUser.getId()));
        model.addAttribute("tasks", taskList);
        this.userAuthCheck.addAuthenticatedUserToModel(model);
        return "index";
    }
}
