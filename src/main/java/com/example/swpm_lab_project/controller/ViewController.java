package com.example.swpm_lab_project.controller;

import com.example.swpm_lab_project.entity.Student;
import com.example.swpm_lab_project.repository.StudentRepository;
import com.example.swpm_lab_project.repository.CourseRepository;
import org.springframework.security.core.Authentication; // Add this import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public ViewController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping({"/", "/dashboard"})
    public String showDashboard(Model model, Authentication authentication) {
        // Get the name of the logged-in user (e.g., "student" or "teacher")
        String loggedInUsername = authentication.getName();

        // Check if the user has the TEACHER role
        boolean isTeacher = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"));

        if (isTeacher) {
            // Teachers see the full list of students
            model.addAttribute("students", studentRepository.findAll());
        } else {
            // Students only see their own record matching their login username
            List<Student> me = studentRepository.findByName(loggedInUsername);
            model.addAttribute("students", me);
        }

        model.addAttribute("courses", courseRepository.findAll());
        return "dashboard";
    }
}