package com.practice.students.service;

import com.practice.students.entity.Course;
import com.practice.students.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCoursesByStudentId(int studentId){
        List<Course> courses = new ArrayList<>();

        courseRepository.findByStudentsId(studentId).forEach(courses::add);

        return courses;
    }

    public void addCourse(Course course){
        course.setId(course.getName());
        courseRepository.save(course);

    }

    public void updateCourse(String id, Course course) {
        course.setId(id);
        courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return (List<Course>)courseRepository.findAll();
    }
}
