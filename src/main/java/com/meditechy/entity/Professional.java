package com.meditechy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "professionals")
public class Professional extends BaseEntity{
    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "Specialty", length = 100)
    private String specialty;

    @Column(name = "License", length = 20)
    private String license;

    @ManyToOne
    @JoinColumn(name = "hospital_id") // Define the foreign key column
    private Hospital hospital;

    @Column(name = "Department", length = 100)
    private String department;

    @Column(name = "Credentials", length = 20)
    private String credentials;

    @Column(name = "HospitalID")
    private int hospitalId;

    @ManyToMany(mappedBy = "professionals", cascade = CascadeType.ALL)
    private List<Patient> patients;
}
