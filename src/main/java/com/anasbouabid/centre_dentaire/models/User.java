package com.anasbouabid.centre_dentaire.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@NoArgsConstructor
@Getter
@Setter
public class User extends Person{
    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String fullName, String avatar, String idNumber, EGender gender, String phoneNumber,
                LocalDateTime birthday, LocalDateTime createdAt, String username, String email, String password) {
        super(fullName, avatar, idNumber, gender, phoneNumber, birthday, createdAt);
        this.username = username;
        this.email = email;
        this.password = password;
    }
}