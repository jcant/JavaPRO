package com.gmail.gm.jcant.javaPro;

import com.gmail.gm.jcant.javaPro.DAO.EntityDAO;
import com.gmail.gm.jcant.javaPro.Entities.Client;
import com.gmail.gm.jcant.javaPro.Entities.Order;
import com.gmail.gm.jcant.javaPro.Entities.Product;
import com.gmail.gm.jcant.javaPro.JDBC.ClientJDBC;
import com.gmail.gm.jcant.javaPro.JDBC.OrderJDBC;
import com.gmail.gm.jcant.javaPro.JDBC.ProductJDBC;

public class ServiceController {
    private EntityDAO<Client> client;
    private EntityDAO<Order> order;
    private EntityDAO<Product> product;

    public ServiceController() {
        super();
        client = new ClientJDBC();
        order = new OrderJDBC();
        product = new ProductJDBC();
    }

    public Client getClient(int id){
        return client.get(id);
    }

    public Product getProduct(int id){
        return product.get(id);
    }

    public void showClientList(){
        client.getAll().forEach(n-> System.out.println(n));
    }

    public void showProductList(){
        product.getAll().forEach(n-> System.out.println(n));
    }

    public void showClientsOrder(int clientId){
        System.out.println(client.get(clientId).getName() + " orders:");
        for (Order order: order.getBy("ID_CLIENT = "+clientId)) {
            System.out.println(product.get(order.getIdProduct()));
        }
    }

    public void showProductsInteresting(int productId){
        System.out.println("Product: "+product.get(productId).getName());
        for (Order order: order.getBy("ID_PRODUCT = "+productId)){
            System.out.println(client.get(order.getIdClient()));
        }
    }

    public void addOrder(int clientId, int productId){
        order.add(new Order(clientId, productId));
    }


}
