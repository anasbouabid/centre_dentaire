package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Allergy;
import com.anasbouabid.centre_dentaire.repository.AllergyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AllergyServiceImpl implements AllergyService {
    private AllergyRepository allergyRepository;

    public AllergyServiceImpl(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    @Override
    public List<Allergy> getAllAllergies() {
        return this.allergyRepository.findAll();
    }

    @Override
    public Allergy getAllergyById(long id) {
        Optional<Allergy> optionalAllergy = this.allergyRepository.findById(id);
        Allergy allergy;
        if (optionalAllergy.isPresent()) {
            allergy = optionalAllergy.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return allergy;
    }

    @Override
    public void saveOrUpdateAllergy(Allergy allergy) {
        this.allergyRepository.save(allergy);
    }

    @Override
    public void deleteAllergyById(long id) {
        this.allergyRepository.deleteById(id);
    }
}
