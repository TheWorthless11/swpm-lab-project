package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Course;
import com.example.swpm_lab_project.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // Enables Mockito for unit testing
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository; // Our "fake" repository

    @InjectMocks
    private CourseService courseService; // The service we are testing

    @Test
    void addCourseFromUI() {
        // 1. Act: Call the method with sample data
        courseService.addCourseFromUI("Software Engineering", "CSE 3200");

        // 2. Assert: Verify that the repository's save  method was called exactly once
        // This proves the service successfully processed the UI input and sent it to the database layer.
        verify(courseRepository, times(1)).save(any(Course.class));
    }
}