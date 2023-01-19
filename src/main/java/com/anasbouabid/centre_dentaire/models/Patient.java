package com.anasbouabid.centre_dentaire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@Getter
@Setter
public class Patient extends Person {
    @Size(max = 50)
    @Email
    private String email;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "patient_photos", joinColumns = @JoinColumn(name="patient_id"))
    private Set<String> photos = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "patient_allergies",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private Set<Allergy> allergies = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patient_diseases",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private Set<Disease> diseases = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "patient_drugs",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id"))
    private Set<Drug> drugs = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new LinkedHashSet<>();
}
