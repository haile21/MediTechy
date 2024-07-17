package com.meditechy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "hospitals")
public class Hospital extends BaseEntity{
    @Column(name = "HospitalName", length = 100)
    private String hospitalName;

    @Column(name = "Address", length = 200)
    private String address;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "EmailAddress", length = 100)
    private String emailAddress;
}
