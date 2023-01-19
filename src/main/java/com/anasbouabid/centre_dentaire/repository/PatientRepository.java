package com.anasbouabid.centre_dentaire.repository;

import com.anasbouabid.centre_dentaire.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
