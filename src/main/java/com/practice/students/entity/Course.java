package com.practice.students.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    private String id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "courses",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> students;

    public Course() {
    }

    public Course(String name, String description, List<Student> students) {

        setId(name);
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.replaceAll("\\s","_");;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
