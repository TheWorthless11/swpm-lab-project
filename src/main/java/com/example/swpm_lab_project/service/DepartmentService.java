package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Department;
import com.example.swpm_lab_project.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this as the business logic layer
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        // Example Logic: Force department name to uppercase
        if (department.getName() != null) {
            department.setName(department.getName().toUpperCase());
        }
        return departmentRepository.save(department);
    }
}