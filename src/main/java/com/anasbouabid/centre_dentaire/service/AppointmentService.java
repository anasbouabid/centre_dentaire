package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(long id);

    void saveOrUpdateAppointment(Appointment appointment);

    void deleteAppointmentById(long id);

}
