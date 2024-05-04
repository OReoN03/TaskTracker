package com.example.tasktracker.repository.card;

import com.example.tasktracker.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    List<Card> findAll();

    Card save(Card card);

    Optional<Card> findById(Integer id);

    void deleteById(Integer id);
}
