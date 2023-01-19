package com.anasbouabid.centre_dentaire.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String fullName;

    @NotBlank
    private String avatar;

    @NotBlank
    @Size(max = 20)
    private String idNumber;

    @Size(max = 50)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @NotBlank
    @Size(max=50)
    private String phoneNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime birthday;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    public Person(String fullName, String avatar, String idNumber, EGender gender, String phoneNumber,
                  LocalDateTime birthday, LocalDateTime createdAt) {
        this.fullName = fullName;
        this.avatar = avatar;
        this.idNumber = idNumber;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }
}
