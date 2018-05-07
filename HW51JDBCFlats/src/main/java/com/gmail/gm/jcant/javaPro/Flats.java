package com.gmail.gm.jcant.javaPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Flats {

	private Connection conn = null;
	private DbProperties props = new DbProperties();

	public Flats() {
		super();
		try {
			prepare();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void prepare() throws SQLException {
		conn = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword());
	}

	public void addFlat(Flat flat) {
		try (PreparedStatement ps = conn
				.prepareStatement("INSERT INTO FLATS (DISTRICT, ADDRESS, AREA, ROOMS, PRICE) VALUES (?, ?, ?, ?, ?)")) {
			ps.setString(1, flat.getDistrict());
			ps.setString(2, flat.getAddress());
			ps.setDouble(3, flat.getArea());
			ps.setInt(4, flat.getRooms());
			ps.setInt(5, flat.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Flat getFlat(int id) throws SQLException {

		return getFlatsBy("ID = " + id)[0];
	}

	public Flat[] getFlatsByDistrict(String district) throws SQLException {

		return getFlatsBy("DISTRICT LIKE '" + district + "'");
	}

	public Flat[] getFlatsPriceLess(int price) throws SQLException {

		return getFlatsBy("PRICE < " + price);
	}

	public Flat[] getFlatsPriceMore(int price) throws SQLException {

		return getFlatsBy("PRICE > " + price);
	}

	public Flat[] getFlatsPriceBetween(int price1, int price2) throws SQLException {

		return getFlatsBy("PRICE > " + price1 + " AND PRICE < " + price2);
	}

	public Flat[] getFlatsBy(String condition) throws SQLException {
		List<Flat> flats = new ArrayList<>();
		String query = "SELECT ID, DISTRICT, ADDRESS, AREA, ROOMS, PRICE FROM FLATS WHERE %s";
		try (PreparedStatement ps = conn.prepareStatement(String.format(query, condition))) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Flat flat = new Flat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),
							rs.getInt(6));
					flats.add(flat);
				}
			}

		}
		return flats.toArray(new Flat[] { null });
	}

	public void createFlatsTable() {
		try (Statement st = conn.createStatement()) {
			st.execute("DROP TABLE IF EXISTS FLATS;");
			st.execute(
					"CREATE TABLE FLATS(ID int(11) NOT NULL AUTO_INCREMENT, DISTRICT VARCHAR(100), ADDRESS VARCHAR(200), AREA FLOAT, ROOMS int, PRICE int, PRIMARY KEY (ID));");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
