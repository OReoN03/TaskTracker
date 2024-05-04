package com.example.tasktracker.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449326074498337970L;

    @Id
    @GeneratedValue
    private Integer id;

    @NaturalId
    private String login;

    private String firstName;
    private String patronymic;
    private String lastName;

    private String hashPassword;

    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public String getFullName() {
        String fullName = lastName + " " + firstName;
        if (patronymic != null) {
            fullName += " " + patronymic;
        }
        return fullName;
    }
}
