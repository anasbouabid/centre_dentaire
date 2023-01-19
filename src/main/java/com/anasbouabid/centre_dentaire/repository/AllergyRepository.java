package com.anasbouabid.centre_dentaire.repository;

import com.anasbouabid.centre_dentaire.models.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}
