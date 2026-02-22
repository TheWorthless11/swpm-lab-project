package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.repository.CourseRepository;
import com.example.swpm_lab_project.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito for unit testing
class DashboardServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private Authentication authentication; // Mocking the security context

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void getStudentsForUser_AsTeacher() {
        // Arrange: Mock the user having the 'ROLE_TEACHER' authority
        when(authentication.getAuthorities()).thenReturn((List) List.of(new SimpleGrantedAuthority("ROLE_TEACHER")));

        // Act
        dashboardService.getStudentsForUser(authentication);

        // Assert: Verify that a teacher triggers the 'findAll' method
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentsForUser_AsStudent() {
        // Arrange: Mock a normal student login
        String username = "2107081";
        when(authentication.getName()).thenReturn(username);
        when(authentication.getAuthorities()).thenReturn((List) List.of(new SimpleGrantedAuthority("ROLE_STUDENT")));

        // Act
        dashboardService.getStudentsForUser(authentication);

        // Assert: Verify students only trigger a search by name
        verify(studentRepository, times(1)).findByName(username);
    }
}