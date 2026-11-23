package controller;

import entity.User;
import exception.UserServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String readAll(Model model) throws UserServiceException {
        model.addAttribute("users", userService.readAll());
        return "users";
    }

    @PostMapping
    public String add(@ModelAttribute User user) throws UserServiceException {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) throws UserServiceException {
        model.addAttribute("user", userService.read(id));
        return "user";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute User user) throws UserServiceException {
        user.setId(id);
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) throws UserServiceException {
        userService.delete(id);
        return "redirect:/users";
    }
}
