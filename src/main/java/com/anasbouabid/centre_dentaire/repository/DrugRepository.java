package com.anasbouabid.centre_dentaire.repository;

import com.anasbouabid.centre_dentaire.models.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
}
