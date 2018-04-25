package com.gmail.gm.jcant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Voenkom, Cloneable, Serializable {

	private static final long serialVersionUID = 5L;

	private final int GROUPSIZE = 10;
	private String groupName;
	private List<Student> students = new ArrayList<>();

	public Group() {
		super();
	}

	public Group(String groupName) {
		super();
		this.groupName = groupName;
	}

	public Group(String groupName, Student[] students) {
		super();
		this.groupName = groupName;
		initGroupArray(students);
	}

	private final void initGroupArray(Student[] students) {
		if (students == null) {
			throw new IllegalArgumentException("students array is null. Cant't init");
		}

		if (students.length > GROUPSIZE) {
			throw new IndexOutOfBoundsException("Too big init array!");
		} else {
			for (Student student : students) {
				addStudent(student);
			}
		}
	}

	public void addStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("student is null");
		}

		if (students.size() >= GROUPSIZE) {
			throw new IndexOutOfBoundsException("Too many students - group is full!");
		} else {
			students.add(student);
			student.setGroup(this);
		}
	}

	public void deleteStudent(int studentNumber) {
		if ((studentNumber >= students.size()) || (studentNumber < 0)) {
			throw new IllegalArgumentException("Wrong student number - can't delete!");
		} else {
			students.remove(studentNumber);
		}
	}

	public void clearGroup() {
		students.clear();
	}

	public Student[] findStudentBySurname(String surname) {
		if (surname == null) {
			throw new IllegalArgumentException("Surname is null");
		}

		List<Student> result = new ArrayList<>();
		Student[] tmpSt = new Student[0];

		for (Student student : students) {
			if (surname.equals(student.getSurname())) {
				result.add(student);
			}
		}

		tmpSt = result.toArray(tmpSt);

		return tmpSt;
	}

	public void sortGroup(Student.SortBy field) {
		Student.setSortArrayBy(field);
		students.sort((s1, s2) -> Student.sortArrayBy.compare(s1, s2));
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getStudentsCount() {
		return students.size();
	}

	private void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student[] getStudentsArray() {
		return (Student[]) students.toArray();
	}

	public Student getStudent(int number) {
		if ((number < 0) || (number >= students.size())) {
			throw new IndexOutOfBoundsException();
		}

		return students.get(number);
	}

	public double getAverageScore() {
		double sum = 0;
		for (Student student : students) {
			sum += student.getAvarageScore();
		}

		return sum / students.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Group: " + groupName + System.lineSeparator());
		for (int i = 0; i < students.size(); i++) {
			sb.append(i + ") ");
			sb.append(students.get(i));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	// Voenkom:
	@Override
	public Student[] conscription() {
		List<Student> tmp = new ArrayList<>();
		Student[] res = new Student[0];

		for (Student student : students) {
			if (student.isMale() && (student.getAge() >= 18)) {
				tmp.add(student);
			}
		}

		res = tmp.toArray(res);
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GROUPSIZE;
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (GROUPSIZE != other.GROUPSIZE)
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		return true;
	}

	@Override
	protected Group clone() {

		Group result = new Group();

		if (groupName != null) {
			result.setGroupName(new String(groupName));
		}

		if (students != null) {

			List<Student> newList = new ArrayList<>();
			for (Student student : students) {
				newList.add(student.clone());
			}

			result.setStudents(newList);

			result.setGroupRef();
		}

		return result;
	}

	private void setGroupRef() {
		if (students != null) {

			for (Student student : students) {
				student.setGroup(this);
			}
		}
	}

}
