package com.anasbouabid.centre_dentaire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private double amount;

    @NotBlank
    private boolean isPaid;

    @NotBlank
    private LocalDateTime dueTime;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Bill(double amount, boolean isPaid, LocalDateTime dueTime, Appointment appointment) {
        this.amount = amount;
        this.isPaid = isPaid;
        this.dueTime = dueTime;
        this.appointment = appointment;
    }
}