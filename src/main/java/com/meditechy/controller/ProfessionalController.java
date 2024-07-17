package com.meditechy.controller;

import com.meditechy.entity.Professional;
import com.meditechy.service.ProfessionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @PostMapping
    public ResponseEntity<Professional> createProfessional(@RequestBody Professional professional) {
        Professional createdProfessional = professionalService.createProfessional(professional);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessional);
    }

    @GetMapping
    public ResponseEntity<List<Professional>> getAllProfessionals() {
        List<Professional> professionals = professionalService.getAllProfessionals();
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professional> getProfessionalById(@PathVariable Long id) {
        Professional professional = professionalService.getProfessionalById(id);
        return ResponseEntity.ok(professional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professional> updateProfessional(@PathVariable Long id, @RequestBody Professional professional) {
        Professional updatedProfessional = professionalService.updateProfessional(professional, id);
        return ResponseEntity.ok(updatedProfessional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
        return ResponseEntity.noContent().build();
    }
}