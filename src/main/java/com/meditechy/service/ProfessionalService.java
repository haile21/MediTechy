package com.meditechy.service;

import com.meditechy.entity.Professional;
import com.meditechy.exception.ProfessionalNotFoundException;
import com.meditechy.repository.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;

    public Professional createProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }

    public Professional getProfessionalById(Long id) {
        return professionalRepository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundException("Professional with ID " + id + " not found."));
    }

    public Professional updateProfessional(Professional professional, Long id) {
        Professional existingProfessional = getProfessionalById(id);
        // Update relevant fields (avoid overwriting everything)
        existingProfessional.setFirstName(professional.getFirstName());
        existingProfessional.setLastName(professional.getLastName());
        existingProfessional.setPhoneNumber(professional.getPhoneNumber());
        existingProfessional.setSpecialty(professional.getSpecialty());
        existingProfessional.setLicense(professional.getLicense());
        existingProfessional.setHospital(professional.getHospital());
        existingProfessional.setDepartment(professional.getDepartment());
        existingProfessional.setCredentials(professional.getCredentials());

        return professionalRepository.save(existingProfessional);
    }

    public void deleteProfessional(Long id) {
        professionalRepository.deleteById(id);
    }
}