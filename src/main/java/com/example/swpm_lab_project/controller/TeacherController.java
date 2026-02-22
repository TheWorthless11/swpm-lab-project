package com.example.swpm_lab_project.controller;

import com.example.swpm_lab_project.entity.Teacher;
import com.example.swpm_lab_project.service.TeacherService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService; // Using Service instead of Repository

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // 1. READ: Get all teachers
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.findAllTeachers();
    }

    // 2. CREATE: API version
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    // 3. CREATE: UI version for Dashboard
    @PostMapping("/ui-create")
    @PreAuthorize("hasRole('ADMIN')")
    public void addTeacherViaUI(@RequestParam String name,
                                @RequestParam String teacherId,
                                HttpServletResponse response) throws IOException {
        teacherService.registerFromUI(name, teacherId); // Call logic in service
        response.sendRedirect("/dashboard");
    }
}