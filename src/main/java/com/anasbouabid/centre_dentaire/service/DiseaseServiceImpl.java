package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Disease;
import com.anasbouabid.centre_dentaire.repository.DiseaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    private DiseaseRepository diseaseRepository;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public List<Disease> getAllDiseases() {
        return this.diseaseRepository.findAll();
    }

    @Override
    public Disease getDiseaseById(long id) {
        Optional<Disease> optionalDisease = this.diseaseRepository.findById(id);
        Disease disease;
        if (optionalDisease.isPresent()) {
            disease = optionalDisease.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return disease;
    }

    @Override
    public void saveOrUpdateDisease(Disease disease) {
        this.diseaseRepository.save(disease);
    }

    @Override
    public void deleteDiseaseById(long id) {
        this.diseaseRepository.deleteById(id);
    }
}
