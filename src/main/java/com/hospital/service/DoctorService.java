package com.hospital.service;

import com.hospital.entities.Doctor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DoctorService {

    @PersistenceContext(unitName = "HospitalPU")
    private EntityManager em;

    public void addDoctor(Doctor doctor) {
        em.persist(doctor);
    }

    public Doctor updateDoctor(Doctor doctor) {
        return em.merge(doctor);
    }

    public void deleteDoctor(int id) {
        Doctor doctor = em.find(Doctor.class, id);
        if (doctor != null) {
            em.remove(doctor);
        }
    }

    public Doctor findDoctor(int id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> getAllDoctors() {
        return em.createQuery(
            "SELECT d FROM Doctor d", Doctor.class)
            .getResultList();
    }
}