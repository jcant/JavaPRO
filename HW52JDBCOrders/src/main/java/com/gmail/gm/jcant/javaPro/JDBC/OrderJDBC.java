package com.gmail.gm.jcant.javaPro.JDBC;

import com.gmail.gm.jcant.javaPro.DAO.EntityDAO;
import com.gmail.gm.jcant.javaPro.DbProperties;
import com.gmail.gm.jcant.javaPro.Entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderJDBC implements EntityDAO<Order> {
    private DbProperties prop = new DbProperties();

    public OrderJDBC() {
        super();
    }

    public OrderJDBC(boolean clean) {
        super();
        if (clean) {
            createTable();
        }
    }

    @Override
    public Order get(int id) {
        List<Order> result = getBy("ID = " + id);
        if (result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public List<Order> getAll() {
        return getBy("true");
    }

    @Override
    public void add(Order order) {
        String sql = "INSERT INTO ORDERS (ID_CLIENT, ID_PRODUCT) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, order.getIdClient());
                ps.setInt(2, order.getIdProduct());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE ORDERS SET (ID_CLIENT = ?, ID_PRODUCT = ?) WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, order.getIdClient());
                ps.setInt(2, order.getIdProduct());
                ps.setInt(3, order.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE ORDERS WHERE ID = ?";
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
    public List<Order> getBy(String condition) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT ID, ID_CLIENT, ID_PRODUCT FROM ORDERS WHERE %s";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(String.format(query, condition))) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                        orders.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void createTable() {
        String sql1 = "DROP TABLE IF EXISTS ORDERS";
        String sql2 = "CREATE TABLE ORDERS (ID INT NOT NULL AUTO_INCREMENT, ID_CLIENT INT NOT NULL, ID_PRODUCT INT NOT NULL, PRIMARY KEY (ID)) ENGINE = InnoDB;";
        String sql3 = "ALTER TABLE ORDERS ADD KEY ID_CLIENT (ID_CLIENT), ADD KEY ID_PRODUCT (ID_PRODUCT);";
        String sql4 = "ALTER TABLE ORDERS " +
                "ADD CONSTRAINT ORDERS_FK1 FOREIGN KEY (ID_CLIENT) REFERENCES CLIENTS (ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "ADD CONSTRAINT ORDERS_FK2 FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCTS (ID) ON DELETE CASCADE ON UPDATE CASCADE;";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (Statement st = conn.createStatement()) {
                //try (PreparedStatement ps = conn.prepareStatement(sql1)) {
                st.addBatch(sql1);
                st.addBatch(sql2);
                //st.executeBatch();
                st.addBatch(sql3);
                st.addBatch(sql4);
                st.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

