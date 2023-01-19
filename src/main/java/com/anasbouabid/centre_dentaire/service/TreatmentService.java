package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Treatment;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getAllTreatments();

    Treatment getTreatmentById(long id);

    void saveOrUpdateTreatment(Treatment treatment);

    void deleteTreatmentById(long id);

}
