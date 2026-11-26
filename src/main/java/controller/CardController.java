package controller;

import exception.CardServiceException;
import exception.InvalidEntityException;
import exception.NotFoundException;
import mapper.CardFormMapper;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import service.CardService;

@Controller
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;
    private final CardFormMapper cardFormMapper;

    public CardController(CardService cardService, CardFormMapper cardFormMapper) {
        this.cardService = cardService;
        this.cardFormMapper = cardFormMapper;
    }

    @GetMapping
    public String readAll(Model model) throws CardServiceException {
        model.addAttribute("cards", cardService.readAll());
        return "cards";
    }

    @PostMapping
    public String add(@ModelAttribute CardForm form) throws CardServiceException {
        cardService.add(cardFormMapper.toNewCard(form));
        return "redirect:/cards";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) throws CardServiceException {
        model.addAttribute("card", cardService.read(id));
        return "card";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute CardForm form) throws CardServiceException {
        cardService.update(cardFormMapper.toUpdateCard(id, form));
        return "redirect:/cards/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) throws CardServiceException {
        cardService.delete(id);
        return "redirect:/cards";
    }

    @ExceptionHandler(CardServiceException.class)
    public ResponseEntity<String> handleCardServiceException(CardServiceException ex) {
        Throwable cause = meaningfulCause(ex);
        if (cause instanceof NotFoundException) {
            return error(HttpStatus.NOT_FOUND, "Card or user not found");
        }
        if (cause instanceof InvalidEntityException || cause instanceof IllegalArgumentException) {
            return error(HttpStatus.BAD_REQUEST, "Invalid card data");
        }
        if (cause instanceof HibernateException) {
            return error(HttpStatus.SERVICE_UNAVAILABLE, "Card storage is unavailable");
        }
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, BindException.class})
    public ResponseEntity<String> handleBadRequest(Exception ex) {
        return error(HttpStatus.BAD_REQUEST, "Invalid request");
    }

    private static Throwable meaningfulCause(Throwable throwable) {
        Throwable cause = throwable;
        while (cause.getCause() != null
                && !(cause instanceof NotFoundException)
                && !(cause instanceof InvalidEntityException)
                && !(cause instanceof IllegalArgumentException)
                && !(cause instanceof HibernateException)) {
            cause = cause.getCause();
        }
        return cause;
    }

    private static ResponseEntity<String> error(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .contentType(MediaType.TEXT_PLAIN)
                .body(message);
    }
}
