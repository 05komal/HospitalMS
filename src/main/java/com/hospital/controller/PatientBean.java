package com.hospital.controller;

import com.hospital.entities.Patient;
import com.hospital.service.PatientService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PatientBean {

    @EJB
    private PatientService patientService;

    private Patient patient = new Patient();
    private List<Patient> patientList;

    public String addPatient() {
        patientService.addPatient(patient);
        patient = new Patient();
        return "patients?faces-redirect=true";
    }

    public String deletePatient(int id) {
        patientService.deletePatient(id);
        return "patients?faces-redirect=true";
    }

    public List<Patient> getPatientList() {
        if (patientList == null) {
            patientList = patientService.getAllPatients();
        }
        return patientList;
    }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { 
        this.patient = patient; 
    }
}