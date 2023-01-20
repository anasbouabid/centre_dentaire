package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.Treatment;
import com.anasbouabid.centre_dentaire.service.TreatmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    private TreatmentServiceImpl treatmentService;

    public TreatmentController(TreatmentServiceImpl treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public List<Treatment> getTreatments() {
        return this.treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public Treatment getTreatment(@PathVariable long id) {
        return this.treatmentService.getTreatmentById(id);
    }

    @PostMapping
    public long createTreatment(@RequestBody Treatment treatment) {
        this.treatmentService.saveOrUpdateTreatment(treatment);
        return treatment.getId();
    }

    @PutMapping
    public Treatment updateTreatment(@RequestBody Treatment treatment) {
        this.treatmentService.saveOrUpdateTreatment(treatment);
        return treatment;
    }

    @DeleteMapping("/{id}")
    public void deleteTreatment(@PathVariable long id) {
        this.treatmentService.deleteTreatmentById(id);
    }

}
