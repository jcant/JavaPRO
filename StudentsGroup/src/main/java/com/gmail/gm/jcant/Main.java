package com.gmail.gm.jcant;

import java.util.Date;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		Student[] st1 = generateStudentArray(8, "Name", "Surname", JDate.getDate("01-01-1995"),
				JDate.getDate("01-09-2009"));

		// create group then fill it by .addStudent() method
		Group gr1 = new Group("Group One");
		for (Student student : st1) {
			gr1.addStudent(student);
		}

		// create group and initialize it by Student[]
		Group gr2 = new Group("Group Two", st1);

		System.out.println(gr1);
		System.out.println(gr2);
		System.out.println("is equals gr1 and gr2 => " + gr1.equals(gr2)); // false, because groups have different
																			// groupNames ("Group one" and "Group Two")

		gr2.sortGroup(Student.SortBy.SURNAME.setAscending(false));

		System.out.println();
		System.out.println("Group print after sort by Surname (desc):");
		System.out.println(gr2);

		voenkomTest(gr1);
		findBySurnameTest(gr2, "Surname1");
		String surname = gr2.getStudent(0).getSurname();
		findBySurnameTest(gr2, surname);

	}

	public static Student[] generateStudentArray(int count, String name, String sname, Date birthday, Date in) {
		Student[] result = new Student[count];

		Random rr = new Random();

		for (int i = 0; i < count; i++) {
			result[i] = new Student(name + i, sname + rr.nextInt(count * 2), JDate.incYear(birthday, i), ((i % 2) == 0),
					2 * i, 2 * i, "Inst" + i, JDate.incYear(in, i), i * 3);
		}

		return result;
	}

	private static void voenkomTest(Group gr) {
		Student[] army = gr.conscription();
		System.out.println();
		System.out.println("Voenkom: ");
		for (Student student : army) {
			System.out.println(student);
		}
	}

	private static void findBySurnameTest(Group gr, String surname) {
		Student[] result = gr.findStudentBySurname(surname);
		System.out.println();
		System.out.println("Group '" + gr.getGroupName() + "'");
		if (result.length != 0) {
			System.out.println(" Students with Surname equals '" + surname + "'");
			for (Student student : result) {
				System.out.println(student);
			}
		} else {
			System.out.println("The group does't have Students with Surname equals '" + surname + "'");
		}
	}

}
