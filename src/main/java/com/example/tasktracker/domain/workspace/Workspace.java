package com.example.tasktracker.domain.workspace;

import com.example.tasktracker.domain.board.Board;
import com.example.tasktracker.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workspace")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workspace_id_generator")
    @SequenceGenerator(name = "board_id_generator", sequenceName = "sq_workspace_id", allocationSize = 1)
    private int id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "workspace")
    private java.util.List<Board> boards;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "workspace_admin",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private java.util.List<User> admins;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "workspace_guest",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private java.util.List<User> guests;

    public Workspace(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
