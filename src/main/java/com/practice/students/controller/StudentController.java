package com.practice.students.controller;

import com.practice.students.entity.Course;
import com.practice.students.entity.Student;
import com.practice.students.service.CourseService;
import com.practice.students.service.StudentService;
import com.practice.students.utils.StudentServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;



    @GetMapping(path = "/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @PostMapping(path = "/students/")
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @GetMapping(path = "/students/{id}")
    public ResponseEntity getStudent(@PathVariable int id){
        Student student = studentService.getStudent(id);

            if(student != null ){
                return new ResponseEntity<>(student,HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("No student found with id ["+id+"]",HttpStatus.NOT_FOUND);
    }
    @PutMapping(path = "/students/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable int id){

        studentService.updateStudent(student, id);
    }

    @DeleteMapping(path = "/students/{id}")
    public void removeStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }




}
