package com.practice.students.repository;

import com.practice.students.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository <Course,String> {

    List<Course> findByStudentsId(Integer studentId);
}
