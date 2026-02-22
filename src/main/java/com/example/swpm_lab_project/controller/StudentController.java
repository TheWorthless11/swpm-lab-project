package com.example.swpm_lab_project.controller;

import com.example.swpm_lab_project.entity.Student;
import com.example.swpm_lab_project.service.StudentService; // Import service
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService; // Use Service here

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/ui-create")
    @PreAuthorize("hasRole('TEACHER')")
    public void addStudentViaUI(@RequestParam String name,
                                @RequestParam String studentId,
                                HttpServletResponse response) throws IOException {
        studentService.registerFromUI(name, studentId);
        response.sendRedirect("/dashboard");
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public void deleteStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public void deleteStudentViaUI(@PathVariable Long id, HttpServletResponse response) throws IOException {
        studentService.removeStudent(id);
        response.sendRedirect("/dashboard");
    }
}