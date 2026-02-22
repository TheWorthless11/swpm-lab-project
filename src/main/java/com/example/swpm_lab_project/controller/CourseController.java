package com.example.swpm_lab_project.controller;

import com.example.swpm_lab_project.entity.Course;
import com.example.swpm_lab_project.repository.CourseRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // READ: Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // CREATE: Only a TEACHER can create a course (Requirement)
    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PostMapping("/ui-create")
    @PreAuthorize("hasRole('TEACHER')")
    public void createCourseViaUI(@RequestParam String title,
                                  @RequestParam String courseCode,
                                  HttpServletResponse response) throws IOException { // Add these two parameters
        Course newCourse = new Course();
        newCourse.setTitle(title);
        newCourse.setCourseCode(courseCode);
        courseRepository.save(newCourse);

        response.sendRedirect("/dashboard"); // Now 'response' will work!
    }
}