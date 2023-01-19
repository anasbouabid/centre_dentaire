package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.Patient;
import com.anasbouabid.centre_dentaire.service.PatientService;
import com.anasbouabid.centre_dentaire.service.PatientServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/")
    private long createPatient(@RequestBody Patient patient)
    {
        patientService.saveOrUpdatePatient(patient);
        return patient.getId();
    }

    @PutMapping("/")
    private Patient update(@RequestBody Patient patient)
    {
        patientService.saveOrUpdatePatient(patient);
        return patient;
    }

    @DeleteMapping("/{id}")
    private void deletePatient(@PathVariable long id)
    {
        patientService.deletePatientById(id);
    }
}
