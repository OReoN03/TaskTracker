package com.example.tasktracker.adapter.persistence.card;

import com.example.tasktracker.app.api.card.CardRepository;
import com.example.tasktracker.domain.card.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CardRepositoryAdapter implements CardRepository {
    private final CardJpaRepository cardJpaRepository;

    @Override
    public List<Card> findAll() {
        return cardJpaRepository.findAll();
    }

    @Override
    public Card save(Card task) {
        return cardJpaRepository.save(task);
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardJpaRepository.findById(id);
    }
}
