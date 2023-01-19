package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Allergy;

import java.util.List;

public interface AllergyService {
    List<Allergy> getAllAllergies();

    Allergy getAllergyById(long id);

    void saveOrUpdateAllergy(Allergy allergy);

    void deleteAllergyById(long id);

}
