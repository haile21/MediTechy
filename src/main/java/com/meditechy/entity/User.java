package com.meditechy.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meditechy.constants.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String firstname;

    private String lastname;

    @Pattern(regexp = "^251[97]\\d{8}$", flags = Pattern.Flag.UNICODE_CASE)
    @NotBlank(message = "phone is mandatory")
    private String phoneNumber;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;


    private String photo;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 16)
    @JsonIgnore
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    private List<Role> roles = new ArrayList<>();

    @UpdateTimestamp
    private Date updatedAt;

    @CreationTimestamp
    private Date createdAt;


    public void addRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    // Check if the user has a specific role
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public void performTask() {
        if (roles.contains(Role.DOCTOR)) {
            System.out.println("Doctor performing medical tasks.");
        } else if (roles.contains(Role.NURSE)) {
            System.out.println("Nurse handling patient care.");
        }
    }
}