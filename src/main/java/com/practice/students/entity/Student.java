package com.practice.students.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String surname;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
//@ManyToMany(
            fetch = FetchType.LAZY)
    @JoinTable
//    @JoinTable(name = "student_course",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id") )
    private List<Course> courses;

//    public Student(String name, String surname) {
//        this.name = name;
//        this.surname = surname;
//    }

    public Student() {
        this.courses = new ArrayList<>();
    }

    public Student(String name, String surname, List<Course> courses) {
        super();
        this.name = name;
        this.surname = surname;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId() == student.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
