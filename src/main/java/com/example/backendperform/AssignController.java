package com.example.backendperform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignController {

    @Autowired
    private AssignRepository assignmentRepository;

    // Get All Task
    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    // Created Task By Teacher
    @PostMapping
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        // Setting Default Status
        if (assignment.getSubmissionStatus() == null) {
            assignment.setSubmissionStatus("NOT_SUBMITTED");
        }
        return assignmentRepository.save(assignment);
    }

    // Update task (sent assignment/note or give score/note)
    @PutMapping("/{id}")
    public Assignment updateAssignment(@PathVariable Long id, @RequestBody Assignment assignmentDetails) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow();

        // Update Data
        if(assignmentDetails.getSubmissionStatus() != null) assignment.setSubmissionStatus(assignmentDetails.getSubmissionStatus());
        if(assignmentDetails.getScore() != null) assignment.setScore(assignmentDetails.getScore());
        if(assignmentDetails.getStudentNote() != null) assignment.setStudentNote(assignmentDetails.getStudentNote());
        if(assignmentDetails.getInstructorNote() != null) assignment.setInstructorNote(assignmentDetails.getInstructorNote());

        return assignmentRepository.save(assignment);
    }
}