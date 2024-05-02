package com.example.tasktracker.domain.board;

import com.example.tasktracker.domain.list.List;
import com.example.tasktracker.domain.user.User;
import com.example.tasktracker.domain.workspace.Workspace;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "board")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_id_generator")
    @SequenceGenerator(name = "board_id_generator", sequenceName = "sq_board_id", allocationSize = 1)
    private int id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("workspaceId")
    private Workspace workspace;

    @OneToMany(mappedBy = "board")
    private java.util.List<List> lists;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "board_admin",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private java.util.List<User> admins;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "board_guest",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private java.util.List<User> guests;

    public Board(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
