package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Student;
import com.example.swpm_lab_project.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this as the business logic layer
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void registerFromUI(String name, String studentId) {
        Student student = new Student();
        student.setName(name);
        student.setStudentId(studentId);
        studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findByStudentName(String name) {
        return studentRepository.findByName(name);
    }
}