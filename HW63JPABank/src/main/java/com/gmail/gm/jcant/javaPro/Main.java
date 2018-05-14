package com.gmail.gm.jcant.javaPro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPABank");

    public static void main(String[] args) {

        fillCurrency();

        addClients();





    }

    private static void fillCurrency(){
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Currency uah = new Currency("UAH", (double)1/26);
            Currency eur = new Currency("EUR", (double)1/0.9);
            Currency usd = new Currency("USD", 1);

            em.persist(uah);
            em.persist(eur);
            em.persist(usd);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void addClients(){
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Client cl1 = new Client("Ivanov Ivan");
            Client cl2 = new Client("Petrov Petr");
            Client cl3 = new Client("Sidorov Sidor");

            Account cl1Acc = new Account()

            em.persist(cl1);
            em.persist(cl2);
            em.persist(cl3);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
//* Создать базу данных «Банк» с таблицами «Пользователи»,
//        «Транзакции», «Счета» и «Курсы валют». Счет бывает 3-х видов:
//        USD, EUR, UAH. Написать запросы для пополнения счета в нужной
//        валюте, перевода средств с одного счета на другой, конвертации
//        валюты по курсу в рамках счетов одного пользователя. Написать
//        запрос для получения суммарных средств на счету одного
//        пользователя в UAH (расчет по курсу).