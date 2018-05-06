package com.gmail.gm.jcant.javaPro;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException{
        DbProperties props = new DbProperties();

        try (Connection conn = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            try (Statement st = conn.createStatement()) {
                st.execute("DROP TABLE IF EXISTS test");
                st.execute("CREATE TABLE test (data VARCHAR(100), num INT)");
            }

            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO test VALUES (?, ?)")) {
                    for (int i = 0; i < 100; i++) {
                        ps.setString(1, "SomeText" + i);
                        ps.setInt(2, i);
                        ps.executeUpdate();
                    }
                }
                conn.commit();

                System.out.println("Created 100 records in database!");
            } catch (Exception ex) {
                ex.printStackTrace();
                conn.rollback();
            }
        }
    }
}
