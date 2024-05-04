package com.example.tasktracker.service.card;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    List<Card> getAllCards();

    void createCard(Card Card);

    Card findCardById(Integer id) throws ResourceNotFoundException;

    void updateCard(Card card);

    void deleteCard(Integer id);

    Card takeCard(Integer cardId, Integer userId) throws ResourceNotFoundException;

    Card closeCard(Integer cardId) throws ResourceNotFoundException;

    void moveCardToNextList(Card card);
}
