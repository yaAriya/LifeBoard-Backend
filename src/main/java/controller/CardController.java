package controller;

import entity.Card;
import exception.CardServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CardService;

@Controller
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public String readAll(Model model) throws CardServiceException {
        model.addAttribute("cards", cardService.readAll());
        return "cards";
    }

    @PostMapping
    public String add(@ModelAttribute Card card) throws CardServiceException {
        cardService.add(card);
        return "redirect:/cards";
    }


    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) throws CardServiceException {
        model.addAttribute("card", cardService.read(id));
        return "card";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute Card card) throws CardServiceException {
        card.setId(id);
        cardService.update(card);
        return "redirect:/cards/" + card.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) throws CardServiceException {
        cardService.delete(id);
        return "redirect:/cards";
    }
}