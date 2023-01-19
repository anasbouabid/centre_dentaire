package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Disease;

import java.util.List;

public interface DiseaseService {
    List<Disease> getAllDiseases();

    Disease getDiseaseById(long id);

    void saveOrUpdateDisease(Disease disease);

    void deleteDiseaseById(long id);

}
