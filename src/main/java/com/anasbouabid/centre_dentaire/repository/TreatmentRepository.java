package com.anasbouabid.centre_dentaire.repository;

import com.anasbouabid.centre_dentaire.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
