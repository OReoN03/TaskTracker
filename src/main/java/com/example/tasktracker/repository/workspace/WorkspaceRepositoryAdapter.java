package com.example.tasktracker.repository.workspace;

import com.example.tasktracker.model.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepositoryAdapter implements WorkspaceRepository {
    private final WorkspaceJpaRepository workspaceJpaRepository;

    @Override
    public List<Workspace> findAll() {
        return workspaceJpaRepository.findAll();
    }

    @Override
    public Workspace save(Workspace card) {
        return workspaceJpaRepository.save(card);
    }

    @Override
    public Optional<Workspace> findById(Integer id) {
        return workspaceJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        workspaceJpaRepository.deleteById(id);
    }
}
