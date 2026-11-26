package controller;

import entity.User;
import exception.InvalidEntityException;
import exception.NotFoundException;
import exception.UserServiceException;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
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

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<String> handleUserServiceException(UserServiceException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof NotFoundException) {
            return error(HttpStatus.NOT_FOUND, "User not found");
        }
        if (cause instanceof InvalidEntityException || cause instanceof IllegalArgumentException) {
            return error(HttpStatus.BAD_REQUEST, "Invalid user data");
        }
        if (cause instanceof HibernateException) {
            return error(HttpStatus.SERVICE_UNAVAILABLE, "User storage is unavailable");
        }
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, BindException.class})
    public ResponseEntity<String> handleBadRequest(Exception ex) {
        return error(HttpStatus.BAD_REQUEST, "Invalid request");
    }

    private static ResponseEntity<String> error(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .contentType(MediaType.TEXT_PLAIN)
                .body(message);
    }
}
