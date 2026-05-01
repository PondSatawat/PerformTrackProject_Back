package com.example.backendperform;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Student and Teacher Data
    private String studentId;
    private String studentName;
    private String academicYear;

    @Column(name = "teacher_name")
    private String teacherName;

    // Assign Task
    private String taskTitle;
    private String taskDescription;
    private LocalDate dueDate;
    private String subject;
    // Status Score
    private String submissionStatus; // เช่น "NOT_SUBMITTED", "SUBMITTED", "LATE"
    private Double score;

    @Column(columnDefinition = "TEXT")
    private String studentNote;

    @Column(columnDefinition = "TEXT")
    private String instructorNote;
}