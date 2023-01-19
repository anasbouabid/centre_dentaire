package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Treatment;
import com.anasbouabid.centre_dentaire.repository.TreatmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TreatmentServiceImpl implements TreatmentService {
    private TreatmentRepository treatmentRepository;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Treatment> getAllTreatments() {
        return this.treatmentRepository.findAll();
    }

    @Override
    public Treatment getTreatmentById(long id) {
        Optional<Treatment> optionalTreatment = this.treatmentRepository.findById(id);
        Treatment treatment;
        if (optionalTreatment.isPresent()) {
            treatment = optionalTreatment.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return treatment;
    }

    @Override
    public void saveOrUpdateTreatment(Treatment treatment) {
        this.treatmentRepository.save(treatment);
    }

    @Override
    public void deleteTreatmentById(long id) {
        this.treatmentRepository.deleteById(id);
    }
}
