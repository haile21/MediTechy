package com.meditechy.service;

import com.meditechy.entity.Hospital;
import com.meditechy.exception.HospitalNotFoundException;
import com.meditechy.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital with ID " + id + " not found"));
    }

    public Hospital updateHospital(Hospital hospital, Long id) {
        Hospital existingHospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital with ID " + id + " not found"));

        // Update fields (consider using BeanUtils for efficiency)
        existingHospital.setHospitalName(hospital.getHospitalName());
        existingHospital.setAddress(hospital.getAddress());
        existingHospital.setPhoneNumber(hospital.getPhoneNumber());
        existingHospital.setEmailAddress(hospital.getEmailAddress());

        return hospitalRepository.save(existingHospital);
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}