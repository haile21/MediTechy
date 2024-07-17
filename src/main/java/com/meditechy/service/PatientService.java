package com.meditechy.service;


import com.meditechy.entity.Patient;
import com.meditechy.exception.PatientNotFoundException;
import com.meditechy.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@Slf4j
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));
    }

    public Patient updatePatient(Patient patient, Long id) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        try {
            BeanUtils.copyProperties(patient, existingPatient);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Error copying properties", e);
            throw new RuntimeException("Error updating patient", e);
        }

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findPatientsByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }
}