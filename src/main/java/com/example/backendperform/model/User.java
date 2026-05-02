package com.example.backendperform.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // เก็บ UID จาก Firebase เพื่อใช้เป็นตัวอ้างอิงหลัก
    @Column(name = "firebase_uid", unique = true)
    private String firebaseUid;

    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String academicYear;
    private String major;
}