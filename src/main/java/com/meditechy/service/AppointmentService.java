package com.meditechy.service;

import com.meditechy.dto.AppointmentDto;
import com.meditechy.entity.Appointment;
import com.meditechy.entity.Patient;
import com.meditechy.exception.ResourceNotFoundException;
import com.meditechy.repository.AppointmentRepository;
import com.meditechy.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final PatientRepository patientRepository;



    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        return convertToDto(appointment);
    }

    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientService.getPatientById(appointmentDto.getPatientId());
        Appointment appointment = convertToEntity(appointmentDto);
        appointment.setPatient(patient);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDto(savedAppointment);
    }

    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        appointment.setAppointmentDateTime(appointmentDTO.getAppointmentDateTime());
        appointment.setReason(appointmentDTO.getReason());
        appointment.setStatus(appointmentDTO.getStatus());
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return convertToDto(updatedAppointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentDto convertToDto(Appointment appointment) {
        AppointmentDto appointmentDTO = new AppointmentDto();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatientId(appointment.getPatient().getId());
//        appointmentDTO.setDoctorId(appointment.getDoctor().getId());
//        appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDTO.setReason(appointment.getReason());
        appointmentDTO.setStatus(appointment.getStatus());
//        appointmentDTO.setCreatedAt(appointment.getCreatedAt());
//        appointmentDTO.setUpdatedAt(appointment.getUpdatedAt());
        return appointmentDTO;
    }

    private Appointment convertToEntity(AppointmentDto appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());

        // Fetch the Patient and Doctor entities based on the IDs in the DTO
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
//        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
//                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        appointment.setPatient(patient);
       // appointment.setDoctor(doctor);
//        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setReason(appointmentDTO.getReason());
        appointment.setStatus(appointmentDTO.getStatus());
//        appointment.setCreatedAt(appointmentDTO.getCreatedAt());
//        appointment.setUpdatedAt(appointmentDTO.getUpdatedAt());
        return appointment;
    }
}
