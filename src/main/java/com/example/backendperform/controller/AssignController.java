package com.example.backendperform.controller;

import com.example.backendperform.model.Assignment;
import com.example.backendperform.repository.AssignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignController {

    @Autowired
    private AssignRepository assignmentRepository;

    private boolean isLateSubmission(Assignment assignment) {
        if (assignment.getDueDate() == null || assignment.getDueDate().trim().isEmpty()) {
            return false;
        }
        try {
            LocalDateTime dueDate = LocalDateTime.parse(assignment.getDueDate());
            return LocalDateTime.now().isAfter(dueDate);
        } catch (Exception e) {
            try {
                LocalDate dueDate = LocalDate.parse(assignment.getDueDate());
                return LocalDate.now().isAfter(dueDate);
            } catch (Exception ex) {
                return false;
            }
        }
    }

    private void ensureSubmissionTimeAndLateState(Assignment assignment) {
        if (assignment.getSubmissionStatus() != null
                && (assignment.getSubmissionStatus().equals("SUBMITTED") || assignment.getSubmissionStatus().equals("LATE_SUBMITTED"))) {
            if (assignment.getSubmittedAt() == null) {
                assignment.setSubmittedAt(LocalDateTime.now());
            }
            if (assignment.getLateSubmission() == null) {
                assignment.setLateSubmission(isLateSubmission(assignment));
            }
            if (isLateSubmission(assignment) && assignment.getSubmissionStatus().equals("SUBMITTED")) {
                assignment.setSubmissionStatus("LATE_SUBMITTED");
            }
        }
    }

    // Get All Task
    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    // Created Task By Teacher or submitted by Student
    @PostMapping
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        // Setting Default Status for newly created assignments
        if (assignment.getSubmissionStatus() == null) {
            assignment.setSubmissionStatus("NOT_SUBMITTED");
        }
        ensureSubmissionTimeAndLateState(assignment);
        return assignmentRepository.save(assignment);
    }

    // Update task (sent assignment/note or give score/note)
    @PutMapping("/{id}")
    public Assignment updateAssignment(@PathVariable Long id, @RequestBody Assignment assignmentDetails) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow();

        // Update Data
        if (assignmentDetails.getSubmissionStatus() != null) {
            assignment.setSubmissionStatus(assignmentDetails.getSubmissionStatus());
        }
        if (assignmentDetails.getScore() != null) assignment.setScore(assignmentDetails.getScore());
        if (assignmentDetails.getStudentNote() != null) assignment.setStudentNote(assignmentDetails.getStudentNote());
        if (assignmentDetails.getInstructorNote() != null) assignment.setInstructorNote(assignmentDetails.getInstructorNote());
        if (assignmentDetails.getTaskTitle() != null) assignment.setTaskTitle(assignmentDetails.getTaskTitle());
        if (assignmentDetails.getTaskDescription() != null) assignment.setTaskDescription(assignmentDetails.getTaskDescription());
        if (assignmentDetails.getOpenDate() != null) assignment.setOpenDate(assignmentDetails.getOpenDate());
        if (assignmentDetails.getDueDate() != null) assignment.setDueDate(assignmentDetails.getDueDate());
        if (assignmentDetails.getTaskType() != null) assignment.setTaskType(assignmentDetails.getTaskType());
        if (assignmentDetails.getSubject() != null) assignment.setSubject(assignmentDetails.getSubject());
        if (assignmentDetails.getAcademicYear() != null) assignment.setAcademicYear(assignmentDetails.getAcademicYear());
        if (assignmentDetails.getTargetMajor() != null) assignment.setTargetMajor(assignmentDetails.getTargetMajor());

        ensureSubmissionTimeAndLateState(assignment);
        return assignmentRepository.save(assignment);
    }

    // Delete assignment
    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable Long id) {
        assignmentRepository.deleteById(id);
    }
}