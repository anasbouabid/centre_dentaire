package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();

    Patient getPatientById(long id);

    void saveOrUpdatePatient(Patient patient);

    // Patient updatePatient(Patient patient, long id);

    void deletePatientById(long id);

    Page<Patient> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
}
