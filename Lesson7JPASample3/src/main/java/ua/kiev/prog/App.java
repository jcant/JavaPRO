package ua.kiev.prog;

import javax.persistence.*;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        try {
            // #1
            System.out.println("------------------ #1 ------------------");

            em.getTransaction().begin();
            try {
                Course course1 = new Course("Course-1");
                Course course2 = new Course("Course-2");
                Course course3 = new Course("Course-3");
                Course[] courses = {course1, course2, course3};

                course3.setNote("Transient note text");

                for (int i = 0; i < 15; i++) {
                    Client client = new Client("Client-" + i, i);
                    Course course = courses[i % courses.length];
                    course.addClient(client);
                }

                for (Course c : courses)
                    em.persist(c);

                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return;
            }

            // #2
            System.out.println("------------------ #2 ------------------");

            Query query = em.createQuery("SELECT c FROM Client c WHERE c.age < 5", Client.class);
            List<Client> clientList = query.getResultList();

            for (Client client : clientList) {
                System.out.println(client);
            }

            // #3
            System.out.println("------------------ #3 ------------------");

            query = em.createNamedQuery("Course.findAll", Course.class);
            List<Course> courseList = query.getResultList();

            for (Course course : courseList) {
                for (Client client : course.getClients()) {
                    System.out.println(client.getName() + " goes to " + course.getName());
                }
            }

            // #4
            System.out.println("------------------ #4 ------------------");
            try {
                query = em.createNamedQuery("Course.findByName", Course.class);
                query.setParameter("name", "Course-1");
                Course course = (Course) query.getSingleResult();

                for (Client c : course.getClients()) {
                    System.out.println(c);
                }
            } catch (NoResultException ex) {
                System.out.println("Course not found!");
                return;
            } catch (NonUniqueResultException ex) {
                System.out.println("Non unique course found!");
                return;
            }

            // #5
            System.out.println("------------------ #4 ------------------");
        } finally {
            em.close();
            emf.close();
        }
    }
}
