package com.anasbouabid.centre_dentaire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "treatments")
@NoArgsConstructor
@Getter
@Setter
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private double cost;

    @NotBlank
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Treatment(String name, double cost, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.cost = cost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
