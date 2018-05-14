package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            fillTable(10);
            boolean run = true;

            while (run) {
                printMenu();
                int sel = sc.nextInt();
                switch (sel) {
                    case 1:
                        showMenu();
                        break;
                    case 2:
                        addDish();
                        break;
                    case 3:
                        showDiscountDishes();
                        break;
                    case 4:
                        showPricedDishes();
                        break;
                    case 5:
                        showWeightDishes(1000);
                        break;
                    case 6:
                        run = false;
                        break;
                    default:
                        break;
                }
            }
        } finally {
            emf.close();
            sc.close();
        }
    }

    private static void showMenu() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM Menu m");
            List<Menu> menuList = query.getResultList();
            menuList.forEach(m -> System.out.println(m));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void addDish() {
        System.out.println("Name:");
        String name = sc.nextLine();
        System.out.println("Price:");
        double price = sc.nextDouble();
        System.out.println("Weight:");
        int weight = sc.nextInt();
        System.out.println("Has discount? (1-yes, 0-no");
        short disc = sc.nextShort();

        EntityManager em = emf.createEntityManager();
        try {
            Menu dish = new Menu(name, price, weight, disc);
            em.persist(dish);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void showDiscountDishes() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM Menu m where m.discount=1", Menu.class);
            List<Menu> menuList = query.getResultList();
            menuList.forEach(m -> System.out.println(m));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void showPricedDishes() {
        System.out.println("Price from:");
        double from = sc.nextDouble();
        System.out.println("Price to:");
        double to = sc.nextDouble();

        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM Menu m where m.price > :stp and m.price < :enp", Menu.class);
            query.setParameter("stp",from);
            query.setParameter("enp",to);
            List<Menu> menuList = query.getResultList();
            menuList.forEach(m-> System.out.println(m));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void showWeightDishes(int max) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM Menu m", Menu.class);
            List<Menu> menuList = query.getResultList();
            List<Menu> resultMenu = new ArrayList<>();
            int summ = 0;
            for (Menu dish: menuList) {

                if(summ+dish.getWeight() < max){
                    resultMenu.add(dish);
                }

            }

            resultMenu.forEach(m -> System.out.println(m));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void printMenu() {
        System.out.println("1 - Show Menu List");
        System.out.println("2 - Add dish to Menu");
        System.out.println("3 - Show dishes with discount");
        System.out.println("4 - Show dishes with price between:");
        System.out.println("5 - Show dishes list weight less than 1kg");
        System.out.println("6 - Exit");
        System.out.println(">_");


    }

    private static void fillTable(int num) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            List<Menu> menu = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                Menu dish = getRandDish();
                dish.setDishName("Dish" + (i + 1));
                menu.add(dish);
            }

            for (Menu dish : menu) {
                em.persist(dish);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static Menu getRandDish() {
        Random rd = new Random();

        return new Menu("name", (double) rd.nextInt(10000) / 100, rd.nextInt(500), (short) rd.nextInt(2));
    }

}
