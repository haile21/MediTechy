package com.meditechy.controller;

import com.meditechy.dto.MedicalRecordDto;
import com.meditechy.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecordDto> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/{id}")
    public MedicalRecordDto getMedicalRecordById(@PathVariable Long id) {
        return medicalRecordService.getMedicalRecordById(id);
    }

    @PostMapping
    public MedicalRecordDto createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.createMedicalRecord(medicalRecordDto);
    }

    @PutMapping("/{id}")
    public MedicalRecordDto updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.updateMedicalRecord(id, medicalRecordDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
    }
}