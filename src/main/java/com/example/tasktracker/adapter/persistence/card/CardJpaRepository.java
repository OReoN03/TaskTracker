package com.example.tasktracker.adapter.persistence.card;

import com.example.tasktracker.domain.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<Card, Long> {
}
