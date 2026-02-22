package com.example.swpm_lab_project.controller;

import com.example.swpm_lab_project.entity.Course;
import com.example.swpm_lab_project.service.CourseService; // Import the service
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService; // Inject Service instead of Repository

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/ui-create")
    @PreAuthorize("hasRole('TEACHER')")
    public void createCourseViaUI(@RequestParam String title,
                                  @RequestParam String courseCode,
                                  HttpServletResponse response) throws IOException {
        courseService.addCourseFromUI(title, courseCode); // Call Service logic
        response.sendRedirect("/dashboard");
    }
}