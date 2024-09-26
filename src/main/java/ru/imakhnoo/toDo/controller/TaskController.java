package ru.imakhnoo.toDo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.imakhnoo.toDo.entity.MyUser;
import ru.imakhnoo.toDo.entity.Task;
import ru.imakhnoo.toDo.service.TaskService;
import ru.imakhnoo.toDo.service.UserService;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

//    @PostMapping("/add-new-task")
//    public String addNewTask(
//            @RequestParam("name") String name,
//            Principal principal,
//            Model model
//    ) {
//        MyUser myUser = this.userService.findByUsername(principal.getName());
//        int userId = Math.toIntExact(myUser.getId());
//        this.taskService.addNewTask(name, userId);
//        model.addAttribute("user", myUser);
//        return "redirect:/";
//    }


    @PostMapping("/add-new-task")
    @ResponseBody
    public ResponseEntity<Task> addNewTask(
            @RequestParam("name") String name,
            Principal principal
    ) {
        MyUser myUser = this.userService.findByUsername(principal.getName());
        int userId = Math.toIntExact(myUser.getId());
        Task task = this.taskService.addNewTask(name, userId);
        return ResponseEntity.ok(task); // Возвращаем созданную задачу
    }


    @PostMapping("/update-task-status")
    public String updateTaskStatus(
            @RequestParam("taskId") Long taskId,
            @RequestParam("done") boolean done
    ) {
        this.taskService.updateTaskStatus(taskId, done);
        return "redirect:/";
    }

    @PostMapping("/delete-task")
    public CompletableFuture<ResponseEntity<Void>> deleteTask(@RequestParam("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }
}
