package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Drug;
import com.anasbouabid.centre_dentaire.repository.DrugRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DrugServiceImpl implements DrugService {
    private DrugRepository drugRepository;

    public DrugServiceImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public List<Drug> getAllDrugs() {
        return this.drugRepository.findAll();
    }

    @Override
    public Drug getDrugById(long id) {
        Optional<Drug> optionalDrug = this.drugRepository.findById(id);
        Drug drug;
        if (optionalDrug.isPresent()) {
            drug = optionalDrug.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return drug;
    }

    @Override
    public void saveOrUpdateDrug(Drug drug) {
        this.drugRepository.save(drug);
    }

    @Override
    public void deleteDrugById(long id) {
        this.drugRepository.deleteById(id);
    }
}
