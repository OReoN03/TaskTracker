package com.example.tasktracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "workspace")
@Data
public class Workspace implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449326074498337971L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "workspace")
    private java.util.List<Board> boards;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workspace_admin",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private java.util.List<User> admins;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workspace_guest",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private java.util.List<User> guests;
}
