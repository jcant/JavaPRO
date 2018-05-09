package com.gmail.gm.jcant.javaPro;

import com.gmail.gm.jcant.javaPro.DAO.EntityDAO;
import com.gmail.gm.jcant.javaPro.Entities.Client;
import com.gmail.gm.jcant.javaPro.Entities.Order;
import com.gmail.gm.jcant.javaPro.Entities.Product;
import com.gmail.gm.jcant.javaPro.JDBC.ClientJDBC;
import com.gmail.gm.jcant.javaPro.JDBC.OrderJDBC;
import com.gmail.gm.jcant.javaPro.JDBC.ProductJDBC;

import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        //fillClients(new ClientJDBC(true),10);
        //fillProducts(new ProductJDBC(true), 20);
        //fillOrders(new OrderJDBC(true), 30, 10, 20);

        ServiceController sc = new ServiceController();

        //sc.showClientList();
        //sc.showProductList();
        System.out.println("************");
        sc.showClientsOrder(5);
        System.out.println("************");
        sc.showProductsInteresting(1);

        sc.addOrder(5, 1);

        System.out.println("************");
        sc.showClientsOrder(5);

        System.out.println("END");
    }

    private static <T> void printList(List<T> list){
        list.forEach(n -> System.out.println(n));
    }

    private static void fillClients(EntityDAO<Client> client, int count){
        for (int i=1; i<=count; i++){
            client.add(new Client("Name"+i));
        }
    }

    private static void fillProducts(EntityDAO<Product> product, int count){
        Random rd = new Random();
        for (int i=1; i<=count; i++){
            product.add(new Product("Product"+i, Math.rint(100* 500* rd.nextDouble())/100 ));
        }
    }

    private static void fillOrders(EntityDAO<Order> order, int count, int clients, int products){
        Random rd = new Random();
        for (int i=1; i<=count; i++){
            order.add(new Order(1+rd.nextInt(clients-1), 1+rd.nextInt(products-1)));
        }
    }
}
