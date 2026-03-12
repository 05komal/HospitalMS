package com.hospital.service;

import com.hospital.entities.Patient;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PatientService {

    @PersistenceContext(unitName = "HospitalPU")
    private EntityManager em;

    public void addPatient(Patient patient) {
        em.persist(patient);
        em.flush();
    }

    public Patient updatePatient(Patient patient) {
        return em.merge(patient);
    }

    public void deletePatient(int id) {
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            em.remove(patient);
        }
    }

    public Patient findPatient(int id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> getAllPatients() {
        return em.createQuery(
            "SELECT p FROM Patient p", Patient.class)
            .getResultList();
    }
}