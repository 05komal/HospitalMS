package com.hospital.controller;

import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;
import com.hospital.service.AppointmentService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AppointmentBean {

    @EJB
    private AppointmentService appointmentService;

    private Appointment appointment = new Appointment();
    private int selectedPatientId;
    private int selectedDoctorId;
    private List<Appointment> appointmentList;

    public String addAppointment() {
        Patient patient = new Patient();
        patient.setId(selectedPatientId);
        appointment.setPatient(appointmentService
            .getAllPatients()
            .stream()
            .filter(p -> p.getId() == selectedPatientId)
            .findFirst().orElse(null));

        Doctor doctor = appointmentService
            .getAllDoctors()
            .stream()
            .filter(d -> d.getId() == selectedDoctorId)
            .findFirst().orElse(null);

        appointment.setDoctor(doctor);
        appointment.setStatus("Scheduled");
        appointmentService.addAppointment(appointment);
        appointment = new Appointment();
        return "appointments?faces-redirect=true";
    }

    public String deleteAppointment(int id) {
        appointmentService.deleteAppointment(id);
        return "appointments?faces-redirect=true";
    }

    public List<Appointment> getAppointmentList() {
        if (appointmentList == null) {
            appointmentList = appointmentService.getAllAppointments();
        }
        return appointmentList;
    }

    public List<Patient> getPatientList() {
        return appointmentService.getAllPatients();
    }

    public List<Doctor> getDoctorList() {
        return appointmentService.getAllDoctors();
    }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public int getSelectedPatientId() { return selectedPatientId; }
    public void setSelectedPatientId(int selectedPatientId) { this.selectedPatientId = selectedPatientId; }

    public int getSelectedDoctorId() { return selectedDoctorId; }
    public void setSelectedDoctorId(int selectedDoctorId) { this.selectedDoctorId = selectedDoctorId; }
}