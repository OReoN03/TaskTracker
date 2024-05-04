package com.example.tasktracker.repository.list;

import com.example.tasktracker.model.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ListRepositoryAdapter implements ListRepository {
    private final ListJpaRepository listJpaRepository;

    @Override
    public java.util.List<List> findAll() {
        return listJpaRepository.findAll();
    }

    @Override
    public List save(List card) {
        return listJpaRepository.save(card);
    }

    @Override
    public Optional<List> findById(Integer id) {
        return listJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        listJpaRepository.deleteById(id);
    }
}
