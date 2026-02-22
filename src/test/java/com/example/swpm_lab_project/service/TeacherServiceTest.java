package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Teacher;
import com.example.swpm_lab_project.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // Tells JUnit to use Mockito for "fake" objects
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository; // Our "fake" database

    @InjectMocks
    private TeacherService teacherService; // The real service we are testing

    @Test
    void registerFromUI() {
        // 1. Act: Call the function you want to test
        teacherService.registerFromUI("Dr. Masudul Ahsan", "T101");

        // 2. Assert: Verify that the repository's "save" function was actually called once
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }
}