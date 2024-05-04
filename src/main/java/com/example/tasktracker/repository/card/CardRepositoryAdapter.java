package com.example.tasktracker.repository.card;

import com.example.tasktracker.model.Card;
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
    public Optional<Card> findById(Integer id) {
        return cardJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        cardJpaRepository.deleteById(id);
    }
}
