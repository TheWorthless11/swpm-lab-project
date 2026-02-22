package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Department;
import com.example.swpm_lab_project.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void testSaveDepartmentUppercaseLogic() {
        // Arrange: Give it a lowercase name
        Department dept = new Department();
        dept.setName("cse");

        // Act: Save it
        departmentService.saveDepartment(dept);

        // Assert: Verify it was converted to UPPERCASE
        verify(departmentRepository).save(argThat(d -> d.getName().equals("CSE")));
    }
}