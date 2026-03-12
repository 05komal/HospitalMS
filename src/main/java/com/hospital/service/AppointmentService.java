package com.hospital.service;

import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AppointmentService {

    @PersistenceContext(unitName = "HospitalPU")
    private EntityManager em;

    public void addAppointment(Appointment appointment) {
        em.persist(appointment);
        em.flush();
    }

    public void deleteAppointment(int id) {
        Appointment appointment = em.find(Appointment.class, id);
        if (appointment != null) {
            em.remove(appointment);
        }
    }

    public List<Appointment> getAllAppointments() {
        return em.createQuery(
            "SELECT a FROM Appointment a", Appointment.class)
            .getResultList();
    }

    public List<Patient> getAllPatients() {
        return em.createQuery(
            "SELECT p FROM Patient p", Patient.class)
            .getResultList();
    }

    public List<Doctor> getAllDoctors() {
        return em.createQuery(
            "SELECT d FROM Doctor d", Doctor.class)
            .getResultList();
    }
}