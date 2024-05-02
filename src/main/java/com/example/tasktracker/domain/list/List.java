package com.example.tasktracker.domain.list;

import com.example.tasktracker.domain.board.Board;
import com.example.tasktracker.domain.card.Card;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "list")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_id_generator")
    @SequenceGenerator(name = "list_id_generator", sequenceName = "sq_list_id", allocationSize = 1)
    private int id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("boardId")
    private Board board;

    @OneToMany(mappedBy = "list")
    private java.util.List<Card> cards;

    public List(String title) {
        this.title = title;
    }
}
