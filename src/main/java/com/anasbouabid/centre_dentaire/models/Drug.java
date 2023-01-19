package com.anasbouabid.centre_dentaire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "drugs", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
@NoArgsConstructor
@Getter
@Setter
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Drug(String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
