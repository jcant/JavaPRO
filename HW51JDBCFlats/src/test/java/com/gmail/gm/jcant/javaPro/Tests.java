package com.gmail.gm.jcant.javaPro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class Tests {
	private DbProperties props = new DbProperties();
	private Connection conn;

	@Before
	public void prepare() {
		try {
			conn = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword());
		} catch (SQLException e) {
			assertNull(e);
		}
	}

	@After
	public void finish() {
		try {
			conn.close();
		} catch (SQLException e) {
			;
		}
	}

	@Test
	public void testGetData() throws SQLException {
		try (Statement st = conn.createStatement()) {
			try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM test")) {
				assertTrue(rs.next());
			}
		}
	}
}
