package com.meditechy.repository;

import com.meditechy.entity.Hospital;
import com.meditechy.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    List<Professional> findBySpecialty(String specialty);
    List<Professional> findByHospital(Hospital hospital);
}