package com.example.swpm_lab_project.service;

import com.example.swpm_lab_project.entity.Course;
import com.example.swpm_lab_project.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this as the business logic layer
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void addCourseFromUI(String title, String courseCode) {
        Course newCourse = new Course();
        newCourse.setTitle(title);
        newCourse.setCourseCode(courseCode);
        courseRepository.save(newCourse);
    }
}