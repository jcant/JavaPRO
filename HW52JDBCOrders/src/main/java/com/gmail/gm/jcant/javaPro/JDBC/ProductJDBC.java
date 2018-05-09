package com.gmail.gm.jcant.javaPro.JDBC;

import com.gmail.gm.jcant.javaPro.DAO.EntityDAO;
import com.gmail.gm.jcant.javaPro.DbProperties;
import com.gmail.gm.jcant.javaPro.Entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBC implements EntityDAO<Product> {
    private DbProperties prop = new DbProperties();

    public ProductJDBC() {
        super();
    }

    public ProductJDBC(boolean clean) {
        super();
        if (clean) {
            createTable();
        }
    }

    @Override
    public Product get(int id) {
        List<Product> result = getBy("ID = " + id);
        if (result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public List<Product> getAll() {
        return getBy("true");
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO PRODUCTS (NAME, PRICE) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, product.getName());
                ps.setDouble(2, product.getPrice());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE PRODUCTS SET (NAME = ?, PRICE = ?) WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, product.getName());
                ps.setDouble(2, product.getPrice());
                ps.setInt(3, product.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE PRODUCTS WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getBy(String condition) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT ID, NAME, PRICE FROM PRODUCTS WHERE %s";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(String.format(query, condition))) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Product product = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                        products.add(product);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void createTable() {
        String sql1 = "DROP TABLE IF EXISTS ORDERS";
        String sql2 = "DROP TABLE IF EXISTS PRODUCTS";
        String sql3 = "CREATE TABLE PRODUCTS (ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, PRICE DOUBLE NOT NULL, PRIMARY KEY (ID)) ENGINE = InnoDB;";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (Statement st = conn.createStatement()) {
                st.addBatch(sql1);
                st.addBatch(sql2);
                st.addBatch(sql3);
                st.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
