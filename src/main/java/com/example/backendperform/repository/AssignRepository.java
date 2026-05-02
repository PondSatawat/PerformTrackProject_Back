package com.example.backendperform.repository;

import com.example.backendperform.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignRepository extends JpaRepository<Assignment, Long> {
    // FindByStudentId
    List<Assignment> findByStudentId(String studentId);
    // FindByTeacherName
    List<Assignment> findByTeacherName(String teacherName);
}