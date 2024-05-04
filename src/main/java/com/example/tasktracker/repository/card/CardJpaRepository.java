package com.example.tasktracker.repository.card;

import com.example.tasktracker.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<Card, Integer> {
}
