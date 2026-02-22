package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Student;
import com.example.swpm_lab_project.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // Tells JUnit to use Mockito
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository; // This is our "fake" database

    @InjectMocks
    private StudentService studentService; // This is the real service we are testing

    @Test
    void testRegisterFromUI() {
        // 1. Act: Call the function you want to test
        studentService.registerFromUI("Mahhia Mim", "2107081");

        // 2. Assert: Verify that the repository's "save" function was actually called
        // We want to make sure the student was "sent" to the database once.
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}