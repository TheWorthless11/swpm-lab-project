package com.example.swpm_lab_project.controller;

import com.example.swpm_lab_project.entity.Student;
import com.example.swpm_lab_project.repository.StudentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse; // Required for redirection
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. CREATE (For Postman/API)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // 2. CREATE (For UI Dashboard - Only Teachers can add)
    @PostMapping("/ui-create")
    @PreAuthorize("hasRole('TEACHER')")
    public void addStudentViaUI(@RequestParam String name,
                                @RequestParam String studentId,
                                HttpServletResponse response) throws IOException {
        Student student = new Student();
        student.setName(name);
        student.setStudentId(studentId);
        studentRepository.save(student);
        // This forces the dashboard to refresh and show the new student
        response.sendRedirect("/dashboard");
    }

    // 3. READ (All)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 4. DELETE (For API/Postman)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    // 5. DELETE (For UI Dashboard - Only Teachers can delete)
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public void deleteStudentViaUI(@PathVariable Long id, HttpServletResponse response) throws IOException {
        studentRepository.deleteById(id);
        // After deleting, refresh the dashboard
        response.sendRedirect("/dashboard");
    }
}