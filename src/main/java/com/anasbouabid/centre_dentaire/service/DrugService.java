package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Drug;

import java.util.List;

public interface DrugService {
    List<Drug> getAllDrugs();

    Drug getDrugById(long id);

    void saveOrUpdateDrug(Drug drug);

    void deleteDrugById(long id);

}
