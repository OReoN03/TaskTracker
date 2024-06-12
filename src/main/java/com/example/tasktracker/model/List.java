package com.example.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "list")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@RequiredArgsConstructor
public class List implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449326074498337969L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("boardId")
    private Board board;

    @OneToMany(mappedBy = "list")
    private java.util.List<Task> tasks;

    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new java.util.ArrayList<>();
        }
        tasks.add(task);
    }

    public void removeTask(Task task) {
        if (tasks != null) {
            tasks.remove(task);
        }
    }
}
