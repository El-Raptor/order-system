package com.raptor.ordersystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    @EqualsAndHashCode.Include
    private String name;

    @Email(message = "E-mail should be valid")
    @Size(max = 80)
    @NotNull(message = "E-mail cannot be null")
    @Column(unique = true)
    @EqualsAndHashCode.Include
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null")
    private Role role;
}
