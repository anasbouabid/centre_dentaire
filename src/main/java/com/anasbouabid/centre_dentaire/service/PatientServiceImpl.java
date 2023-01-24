package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Disease;
import com.anasbouabid.centre_dentaire.models.Patient;
import com.anasbouabid.centre_dentaire.repository.PatientRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@NoArgsConstructor
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public void saveOrUpdatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        Patient patient;
        if (optionalPatient.isPresent()) {
            patient = optionalPatient.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return patient;
    }

    @Override
    public void deletePatientById(long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient addDiseases(Patient patient, Set<Disease> diseases) {
        diseases.addAll(patient.getDiseases());
        patient.setDiseases(diseases);
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Page<Patient> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.patientRepository.findAll(pageable);
    }
}
