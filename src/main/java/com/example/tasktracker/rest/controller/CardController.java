package com.example.tasktracker.rest.controller;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Card;
import com.example.tasktracker.service.card.CardService;
import com.example.tasktracker.service.list.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final ListService listService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable int id) throws ResourceNotFoundException {
        return cardService.findCardById(id);
    }

    @PostMapping
    public void createCard(@RequestParam Integer listId, @RequestBody Card card) throws ResourceNotFoundException {
        com.example.tasktracker.model.List list = listService.findListById(listId);
        card.setList(list);
        cardService.createCard(card);
    }

    @PutMapping
    public void updateCard(@RequestBody Card card) {
        cardService.updateCard(card);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCard(@PathVariable int id) {
        cardService.deleteCard(id);
    }

    @PostMapping("/{cardId}/take")
    public Card takeCard(@PathVariable int cardId, @RequestParam int userId) throws ResourceNotFoundException {
        return cardService.takeCard(cardId, userId);
    }

    @PostMapping("/{cardId}/close")
    public Card closeCard(@PathVariable int cardId) throws ResourceNotFoundException {
        return cardService.closeCard(cardId);
    }
}
