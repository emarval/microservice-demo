package com.practice.students.controller;

import com.practice.students.entity.Course;
import com.practice.students.service.CourseService;
import com.practice.students.utils.StudentServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoursesController {


    @Autowired
    CourseService courseService;

    @GetMapping(path = "/students/{id}/courses")
    public List<Course> getCoursesByStudentId(@PathVariable("id") int studentId){

        return courseService.getAllCoursesByStudentId(studentId);
    }

    @PostMapping(path = "/courses/")
    public void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }

    @GetMapping(path = "/courses/")
    public List<Course> addCourse(){
        return courseService.getAllCourses();
    }

    @PutMapping(path = "/courses/{id}")
    public void updateCourse(@PathVariable String id, @RequestBody Course course){
        courseService.updateCourse(id,course);

    }


}
