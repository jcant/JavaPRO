package com.gmail.gm.jcant.javaPro;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
		EntityManager em = emf.createEntityManager();
		try {
			fillTables(em);
			
			System.out.println("*** Get all by Criteria ***");
			
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			 
			CriteriaQuery<Course> courseCriteria = cb.createQuery(Course.class);
			Root<Course> courseRoot = courseCriteria.from(Course.class);			
			courseCriteria.select(courseRoot);
			em.createQuery(courseCriteria).getResultList().forEach(n -> System.out.println(n + "\t Students count: "+n.getStudentsCount()));
			em.getTransaction().commit();
			
			
			System.out.println("*** Get all by NamedQuery ***");
			
			Query query = em.createNamedQuery("Course.findAll", Course.class);
	        List<Course> courseList = query.getResultList();

	        for (Course course : courseList) {
	            System.out.print(course);
	            System.out.println("\t Students count: "+course.getStudentsCount());
	        }
	        
	        
		} finally {
			em.close();
			emf.close();
		}
		
		
	}

	private static void fillTables(EntityManager em) {

		em.getTransaction().begin();

		try {
			Course course1 = new Course("Course-1");
			Course course2 = new Course("Course-2");
			Course course3 = new Course("Course-3");
			Course[] courses = { course1, course2, course3 };

			for (int i = 0; i < 15; i++) {
				Student student = new Student("Student-" + i, i);
				Course course = courses[i % courses.length];
				student.addCourse(course);
			}

			for (Course c : courses) {
				em.persist(c);
			}

			em.getTransaction().commit();

		} catch (Exception ex) {
			em.getTransaction().rollback();
		}
	}
}
