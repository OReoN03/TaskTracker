package com.example.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "board")
@Data
public class Board implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449326074498337967L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "board_admin",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private java.util.List<User> admins;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "board_guest",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private java.util.List<User> guests;
}
