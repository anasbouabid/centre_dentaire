package com.anasbouabid.centre_dentaire.controllers;

import com.anasbouabid.centre_dentaire.models.Appointment;
import com.anasbouabid.centre_dentaire.service.AppointmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> getAppointments() {
        return this.appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable long id) {
        return this.appointmentService.getAppointmentById(id);
    }

    @PostMapping
    public long createAppointment(@RequestBody Appointment appointment) {
        this.appointmentService.saveOrUpdateAppointment(appointment);
        return appointment.getId();
    }

    @PutMapping
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        this.appointmentService.saveOrUpdateAppointment(appointment);
        return appointment;
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable long id) {
        this.appointmentService.deleteAppointmentById(id);
    }

}
