package com.example.tasktracker.app.api.card;

import com.example.tasktracker.domain.card.Card;

import java.util.List;

public interface FindAllCardsInbound {
    List<Card> execute();
}
