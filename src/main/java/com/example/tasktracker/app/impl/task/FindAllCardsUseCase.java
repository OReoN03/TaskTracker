package com.example.tasktracker.app.impl.task;

import com.example.tasktracker.app.api.card.FindAllCardsInbound;
import com.example.tasktracker.app.api.card.CardRepository;
import com.example.tasktracker.domain.card.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllCardsUseCase implements FindAllCardsInbound {
    private final CardRepository cardRepository;

    @Override
    public List<Card> execute() {
        return cardRepository.findAll();
    }
}
