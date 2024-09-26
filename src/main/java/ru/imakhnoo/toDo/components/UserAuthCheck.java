package ru.imakhnoo.toDo.components;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.imakhnoo.toDo.entity.MyUser;
import ru.imakhnoo.toDo.service.UserService;

@Component
@RequiredArgsConstructor
public class UserAuthCheck {
    private final UserService userService;

    public void addAuthenticatedUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String userName = authentication.getName();
            MyUser myUser = this.userService.findByUsername(userName);
            model.addAttribute("user", myUser);
        }
    }

}
