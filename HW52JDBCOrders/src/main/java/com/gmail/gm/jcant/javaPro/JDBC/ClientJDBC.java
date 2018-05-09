package com.gmail.gm.jcant.javaPro.JDBC;

import com.gmail.gm.jcant.javaPro.DbProperties;
import com.gmail.gm.jcant.javaPro.Entities.Client;
import com.gmail.gm.jcant.javaPro.DAO.EntityDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientJDBC implements EntityDAO<Client> {
    private DbProperties prop = new DbProperties();

    public ClientJDBC() {
        super();
    }

    public ClientJDBC(boolean clean) {
        super();
        if (clean) {
            createTable();
        }
    }

    @Override
    public Client get(int id) {
        List<Client> result = getBy("ID = " + id);
        if (result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public List<Client> getAll() {
        return getBy("true");
    }

    @Override
    public void add(Client client) {
        String sql = "INSERT INTO CLIENTS (NAME) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, client.getName());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE CLIENTS SET (NAME = ?) WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, client.getName());
                ps.setInt(2, client.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE CLIENTS WHERE ID = ?";
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
    public List<Client> getBy(String condition) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT ID, NAME FROM CLIENTS WHERE %s";
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword())) {
            try (PreparedStatement ps = conn.prepareStatement(String.format(query, condition))) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Client client = new Client(rs.getInt(1), rs.getString(2));
                        clients.add(client);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void createTable() {
        String sql1 = "DROP TABLE IF EXISTS ORDERS";
        String sql2 = "DROP TABLE IF EXISTS CLIENTS";
        String sql3 = "CREATE TABLE CLIENTS (ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, PRIMARY KEY (ID)) ENGINE = InnoDB;";
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
