package com.example.tasktracker.app.api.card;

import com.example.tasktracker.domain.card.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    List<Card> findAll();

    Card save(Card card);

    Optional<Card> findById(Long id);
}
