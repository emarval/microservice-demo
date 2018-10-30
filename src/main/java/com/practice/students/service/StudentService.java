package com.practice.students.service;

import com.practice.students.entity.Course;
import com.practice.students.entity.Student;
import com.practice.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents(){

        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public void addStudent(Student student){
        for (Course course: student.getCourses()) {
            course.setId(course.getName());

        }
        studentRepository.save(student);
    }

    public Student getStudent(int id) {

        Student studentResponse = studentRepository.findById(id).orElse(null);

        if(studentResponse!=null) {

            return studentResponse;

        }
        else return null;

    }

    public void updateStudent(Student student, int id) {
        student.setId(id);
        studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.delete(studentRepository.findById(id).get());
    }
}
