package com.meditechy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient extends BaseEntity{
    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "DateOfBirth")
    private java.time.LocalDate dateOfBirth;

    @Column(name = "Gender", length = 1)
    private char gender;

    @Column(name = "ContactNumber", length = 20)
    private String contactNumber;

    @Column(name = "EmailAddress", length = 100)
    private String emailAddress;

    @Column(name = "Address", length = 200)
    private String address;

    @ManyToMany(cascade = CascadeType.ALL) // Careful with cascade types
    @JoinTable(name = "patient_professional",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "professional_id"))
    private List<Professional> professionals;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();
}
