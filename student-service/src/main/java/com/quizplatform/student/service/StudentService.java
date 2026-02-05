package com.quizplatform.student.service;

import com.quizplatform.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    
    StudentDTO createStudent(StudentDTO studentDTO);
    
    StudentDTO getStudentById(Long id);
    
    StudentDTO getStudentByEmail(String email);
    
    List<StudentDTO> getAllStudents();
    
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    
    void deleteStudent(Long id);
}
