package com.gmail.gm.jcant.javaPro;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="Courses")
@NamedQueries({
        @NamedQuery(name="Course.findAll", query = "SELECT c FROM Course c"),
        @NamedQuery(name="Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name")
})
public class Course {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Transient
    private String note;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    public Course() {}

    public Course(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        if ( ! students.contains(student))
            students.add(student);
        if ( ! student.getCourses().contains(this))
            student.addCourse(this);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }
    
    public int getStudentsCount() {
    	return students.size();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
