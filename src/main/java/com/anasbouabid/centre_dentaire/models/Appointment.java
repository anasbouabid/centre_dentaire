package com.anasbouabid.centre_dentaire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "appointments")
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @NotBlank
    private LocalDateTime dateTime;

    private String description;

    @NotBlank
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private double totalCost;

    @ManyToMany
    @JoinTable(	name = "appointment_treatments",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id"))
    private Set<Treatment> treatments = new HashSet<>();

    @OneToMany(mappedBy = "appointment")
    private List<Bill> bills = new java.util.ArrayList<>();
}
