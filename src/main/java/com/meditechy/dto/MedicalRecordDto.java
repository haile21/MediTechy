package com.meditechy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDto {
    private Long id;
    private Long patientId;
    private String diagnosis;
    private String treatment;
    private LocalDate visitDate;
}