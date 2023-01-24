package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.Patient;
import com.anasbouabid.centre_dentaire.service.PatientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patients")
@PreAuthorize("hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
public class PatientController {
    private PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Patient> getPatient(@PathVariable long id) {
        Patient p = patientService.getPatientById(id);
        if(p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public long createPatient(@RequestBody Patient patient)
    {
        patientService.saveOrUpdatePatient(patient);
        return patient.getId();
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public Patient update(@RequestBody Patient patient)
    {
        patientService.saveOrUpdatePatient(patient);
        return patient;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public void deletePatient(@PathVariable long id)
    {
        patientService.deletePatientById(id);
    }

}
