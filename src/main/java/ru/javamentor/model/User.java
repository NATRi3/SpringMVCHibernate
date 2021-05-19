package ru.javamentor.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id = null;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 to 10")
    private String firstName;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 15, message = "Surname should be between 2 to 15")
    private String surname;

    @Email(message = "Email should be valid")
    private String email;

    public User(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }
}
