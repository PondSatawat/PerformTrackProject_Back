package com.example.backendperform.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

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
    private String targetMajor; // สาขาวิชาเป้าหมาย (เฉพาะตอนครูสั่งงาน)

    @Column(name = "teacher_name")
    private String teacherName;

    // Assign Task
    private String taskTitle;
    private String taskDescription;
    private String taskType;
    private String openDate;
    private String dueDate;
    private String subject;
    // Status and timing
    private String submissionStatus; // เช่น "NOT_SUBMITTED", "SUBMITTED", "LATE_SUBMITTED", "GRADED"
    private Boolean lateSubmission;
    private LocalDateTime submittedAt;
    private Double score;
    private Double maxScore; // คะแนนเต็มของงาน

    @Column(columnDefinition = "TEXT")
    private String studentNote;

    @Column(columnDefinition = "TEXT")
    private String instructorNote;
}