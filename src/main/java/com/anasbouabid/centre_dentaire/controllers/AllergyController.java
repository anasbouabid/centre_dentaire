package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.Allergy;
import com.anasbouabid.centre_dentaire.service.AllergyServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allergies")
public class AllergyController {
    private AllergyServiceImpl allergyService;

    public AllergyController(AllergyServiceImpl allergyService) {
        this.allergyService = allergyService;
    }

    @GetMapping
    public List<Allergy> getAllergies() {
        return this.allergyService.getAllAllergies();
    }

    @GetMapping("/{id}")
    public Allergy getAllergy(@PathVariable long id) {
        return this.allergyService.getAllergyById(id);
    }

    @PostMapping
    public long createAllergy(@RequestBody Allergy allergy) {
        this.allergyService.saveOrUpdateAllergy(allergy);
        return allergy.getId();
    }

    @PutMapping
    public Allergy updateAllergy(@RequestBody Allergy allergy) {
        this.allergyService.saveOrUpdateAllergy(allergy);
        return allergy;
    }

    @DeleteMapping("/{id}")
    public void deleteAllergy(@PathVariable long id) {
        this.allergyService.deleteAllergyById(id);
    }

}
