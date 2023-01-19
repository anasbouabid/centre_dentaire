package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.Disease;
import com.anasbouabid.centre_dentaire.service.DiseaseServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disease")
public class DiseaseController {
    private DiseaseServiceImpl diseaseService;

    public DiseaseController(DiseaseServiceImpl diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public List<Disease> getDiseases() {
        return this.diseaseService.getAllDiseases();
    }

    @GetMapping("/{id}")
    public Disease getDisease(@PathVariable long id) {
        return this.diseaseService.getDiseaseById(id);
    }

    @PostMapping
    public long createDisease(@RequestBody Disease disease) {
        this.diseaseService.saveOrUpdateDisease(disease);
        return disease.getId();
    }

    @PutMapping
    public Disease updateDisease(@RequestBody Disease disease) {
        this.diseaseService.saveOrUpdateDisease(disease);
        return disease;
    }

    @DeleteMapping("/{id}")
    public void deleteDisease(@PathVariable long id) {
        this.diseaseService.deleteDiseaseById(id);
    }

}
