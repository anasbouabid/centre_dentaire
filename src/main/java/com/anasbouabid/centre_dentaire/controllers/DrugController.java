package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.Drug;
import com.anasbouabid.centre_dentaire.service.DrugServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug")
public class DrugController {
    private DrugServiceImpl drugService;

    public DrugController(DrugServiceImpl drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public List<Drug> getDrugs() {
        return this.drugService.getAllDrugs();
    }

    @GetMapping("/{id}")
    public Drug getDrug(@PathVariable long id) {
        return this.drugService.getDrugById(id);
    }

    @PostMapping
    public long createDrug(@RequestBody Drug drug) {
        this.drugService.saveOrUpdateDrug(drug);
        return drug.getId();
    }

    @PutMapping
    public Drug updateDrug(@RequestBody Drug drug) {
        this.drugService.saveOrUpdateDrug(drug);
        return drug;
    }

    @DeleteMapping("/{id}")
    public void deleteDrug(@PathVariable long id) {
        this.drugService.deleteDrugById(id);
    }

}
