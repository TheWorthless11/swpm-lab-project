package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Course;
import com.example.swpm_lab_project.entity.Student;
import com.example.swpm_lab_project.repository.CourseRepository;
import com.example.swpm_lab_project.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this as the business logic layer
public class DashboardService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public DashboardService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getStudentsForUser(Authentication authentication) {
        String loggedInUsername = authentication.getName();

        boolean isTeacher = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"));

        if (isTeacher) {
            return studentRepository.findAll();
        } else {
            return studentRepository.findByName(loggedInUsername);
        }
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}