package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Teacher;
import com.example.swpm_lab_project.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Business Logic Layer
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void registerFromUI(String name, String teacherId) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setTeacherId(teacherId);
        teacherRepository.save(teacher);
    }
}