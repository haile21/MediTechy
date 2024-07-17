package com.meditechy.service;

import com.meditechy.dto.MedicalRecordDto;
import com.meditechy.entity.MedicalRecord;
import com.meditechy.entity.Patient;
import com.meditechy.exception.ResourceNotFoundException;
import com.meditechy.repository.MedicalRecordRepository;
import com.meditechy.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public List<MedicalRecordDto> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        return medicalRecords.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MedicalRecordDto getMedicalRecordById(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found with ID: " + id));
        return convertToDTO(medicalRecord);
    }

    public MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto) {
        Patient patient = patientService.getPatientById(medicalRecordDto.getPatientId());
        MedicalRecord medicalRecord = convertToEntity(medicalRecordDto);
        medicalRecord.setPatient(patient);
        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        return convertToDTO(savedMedicalRecord);
    }

    public MedicalRecordDto updateMedicalRecord(Long id, MedicalRecordDto medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found with ID: " + id));
        medicalRecord.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecord.setTreatment(medicalRecordDTO.getTreatment());
        medicalRecord.setVisitDate(medicalRecordDTO.getVisitDate());
        MedicalRecord updatedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        return convertToDTO(updatedMedicalRecord);
    }

    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    private MedicalRecordDto convertToDTO(MedicalRecord medicalRecord) {
        MedicalRecordDto medicalRecordDTO = new MedicalRecordDto();
        medicalRecordDTO.setId(medicalRecord.getId());
        medicalRecordDTO.setDiagnosis(medicalRecord.getDiagnosis());
        medicalRecordDTO.setTreatment(medicalRecord.getTreatment());
        medicalRecordDTO.setVisitDate(medicalRecord.getVisitDate());
        medicalRecordDTO.setPatientId(medicalRecord.getPatient().getId());
        return medicalRecordDTO;
    }

    private MedicalRecord convertToEntity(MedicalRecordDto medicalRecordDTO) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(medicalRecordDTO.getId());
        medicalRecord.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecord.setTreatment(medicalRecordDTO.getTreatment());
        medicalRecord.setVisitDate(medicalRecordDTO.getVisitDate());

         Patient patient = patientRepository.findById(medicalRecordDTO.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        medicalRecord.setPatient(patient);

        return medicalRecord;
    }
}
