package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Students")
@NamedQuery(name="Student.findAll", query = "SELECT s FROM Student s")
public class Student {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	private int age;
	
	@ManyToMany
	@JoinTable(
			name="StudentCourse",
            joinColumns={@JoinColumn(name="stu_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="course_id", referencedColumnName="id")})
	private List<Course> courses = new ArrayList<>();

	public Student() {
		super();
	}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void addCourse(Course course) {
		if (!courses.contains(course))
			courses.add(course);
		if (!course.getStudents().contains(this))
			course.addStudent(this);
	}

	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
