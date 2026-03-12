package com.hospital.controller;

import com.hospital.entities.Doctor;
import com.hospital.service.DoctorService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class DoctorBean {

    @EJB
    private DoctorService doctorService;

    private Doctor doctor = new Doctor();
    private List<Doctor> doctorList;

    public String addDoctor() {
        doctorService.addDoctor(doctor);
        doctor = new Doctor();
        return "doctors?faces-redirect=true";
    }

    public String deleteDoctor(int id) {
        doctorService.deleteDoctor(id);
        return "doctors?faces-redirect=true";
    }

    public List<Doctor> getDoctorList() {
        if (doctorList == null) {
            doctorList = doctorService.getAllDoctors();
        }
        return doctorList;
    }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}