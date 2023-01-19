package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.Appointment;
import com.anasbouabid.centre_dentaire.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(long id) {
        Optional<Appointment> optionalAppointment = this.appointmentRepository.findById(id);
        Appointment appointment;
        if (optionalAppointment.isPresent()) {
            appointment = optionalAppointment.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return appointment;
    }

    @Override
    public void saveOrUpdateAppointment(Appointment appointment) {
        this.appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(long id) {
        this.appointmentRepository.deleteById(id);
    }
}
