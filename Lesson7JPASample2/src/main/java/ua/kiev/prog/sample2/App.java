package ua.kiev.prog.sample2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        try {
            Group group1 = new Group("Course-1");
            Group group2 = new Group("Course-2");
            Client client;
            long gid1, gid2;

            // #1
            System.out.println("------------------ #1 ------------------");

            for (int i = 0; i < 10; i++) {
                client = new Client("Name" + i, i);
                group1.addClient(client);
            }
            for (int i = 0; i < 5; i++) {
                client = new Client("Name" + i, i);
                group2.addClient(client);
            }

            em.getTransaction().begin();
            try {
                em.persist(group1); // save groups with clients
                em.persist(group2);
                em.getTransaction().commit();

                System.out.println("New group id #1: " + (gid1 = group1.getId()));
                System.out.println("New group id #2: " + (gid2 = group2.getId()));
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return;
            }

            // #2
            System.out.println("------------------ #2 ------------------");
            em.clear();

            group1 = em.find(Group.class, gid1);
            if (group1 == null) {
                System.out.println("Course #1 not found error!");
                return;
            }
            for (Client c : group1.getClients())
                System.out.println("Client :" + c + " from " + c.getGroup().getName());

            group2 = em.find(Group.class, gid2);
            if (group2 == null) {
                System.out.println("Course #2 not found error!");
                return;
            }
            for (Client c : group2.getClients())
                System.out.println("Client :" + c + " from " + c.getGroup().getName());

            // #3
            System.out.println("------------------ #3 ------------------");
            em.clear();

            client = em.find(Client.class, 1L);
            if (client == null) {
                System.out.println("Client not found error!");
                return;
            }

            System.out.print(client);
            System.out.println(" from " + client.getGroup());
        } finally {
            em.close();
            emf.close();
        }
    }
}
