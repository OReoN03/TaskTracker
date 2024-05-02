package com.example.tasktracker.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449326074498337967L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "sq_user_id", allocationSize = 1)
    private int id;

    @NaturalId
    private String login;

    private String firstName;
    private String patronymic;
    private String lastName;

    private String hashPassword;

    private String email;

    public User(String login, String firstName, String patronymic, String lastName, String hashPassword, String email) {
        this.login = login;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.hashPassword = hashPassword;
        this.email = email;
    }

    public String getFullName() {
        String fullName = lastName + " " + firstName;
        if (patronymic != null) {
            fullName += " " + patronymic;
        }
        return fullName;
    }
}
