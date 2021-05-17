package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Phone;
import connection.SingleConnection;
import exceptions.DBException;

public class phoneDao {

	private Connection con;

	public phoneDao() {
		con = SingleConnection.getConnection();
	}

	public void save(Phone phone) {
		String query = "insert into phone (number, type, userRegister) " + "VALUES (?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, phone.getNumber());
			ps.setString(2, phone.getType());
			ps.setLong(3, phone.getUserRegister());

			ps.execute();
			con.commit();

		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new DBException(e.getMessage());
			} catch (SQLException e1) {
				e.printStackTrace();
				throw new DBException(e1.getMessage());
			}

		}

	}

	// Since list phones will perform just a simple selection method, it is a good
	// possible alternative to just throw the Exception

	// Using a Long user to list only certain Phones related to a chosen User
	public List<Phone> listPhone(Long user) {

		List<Phone> phones = new ArrayList<>();

		String query = "select * from phone WHERE userRegister = " + user;

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Phone phone = new Phone();
				phone.setId(rs.getLong("id"));
				phone.setNumber(rs.getString("number"));
				phone.setType(rs.getString("type"));
				phone.setUserRegister(rs.getLong("userregister"));
				phones.add(phone);
			}

		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new DBException(e.getMessage());
			} catch (SQLException e1) {
				e.printStackTrace();
				throw new DBException(e1.getMessage());
			}

		}

		return phones;
	}

	public void deletePhone(String id) {
		String query = "DELETE FROM phone WHERE id = '" + id + "'";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			int updates = pst.executeUpdate();
			con.commit();
			if (updates > 0) {
				System.out.println("Deletion Successfully Completed! Number of Changes -> " + updates);
			} else {
				con.rollback();
				throw new DBException("Unexpected Error! No rows were Deleted!");
			}

		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new DBException("An Error Occurred during User Deletion! Cause: " + e.getMessage());

			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Connection could not be Rolled Back! Cause: " + e1.getMessage());
			}
		}

	}
}
