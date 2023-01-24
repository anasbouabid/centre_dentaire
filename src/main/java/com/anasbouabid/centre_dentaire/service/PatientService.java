package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Disease;
import com.anasbouabid.centre_dentaire.models.Patient;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface PatientService {
    List<Patient> getAllPatients();

    Patient getPatientById(long id);

    void saveOrUpdatePatient(Patient patient);

    // Patient updatePatient(Patient patient, long id);

    void deletePatientById(long id);

    Patient addDiseases(Patient patient, Set<Disease> diseases);

    Page<Patient> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
}
