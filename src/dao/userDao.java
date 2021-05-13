package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Beans;
import connection.SingleConnection;
import exceptions.DBException;

public class userDao {

	private Connection con;

	public userDao() {
		con = SingleConnection.getConnection();
	}

	public void save(Beans user) {
		String query = "insert into \"userLogin\" (username, password, realname, phone, zip, street, neighborhood, city, state, ibgecode) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRealName());
			ps.setString(4, user.getPhone());
			
			ps.setString(5, user.getZip());
			ps.setString(6, user.getStreet());
			ps.setString(7, user.getNeighborhood());
			ps.setString(8, user.getCity());
			ps.setString(9, user.getState());
			ps.setString(10, user.getIbgeCode());
			
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

	// Since listUsers will perform just a simple selection method, it is a good
	// possible alternative to just throw the Exception
	public List<Beans> listUsers() {

		List<Beans> users = new ArrayList<>();

		String query = "select * from public.\"userLogin\"";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Beans user = new Beans();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealName(rs.getString("realname"));
				user.setPhone(rs.getString("phone"));
				user.setZip(rs.getString("zip"));
				user.setStreet(rs.getString("street"));
				user.setNeighborhood(rs.getString("neighborhood"));
				user.setCity(rs.getString("city"));
				user.setState(rs.getString("state"));
				user.setIbgeCode(rs.getString("ibgecode"));
				users.add(user);
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

		return users;
	}

	public void deleteUser(String id) {
		String query = "DELETE FROM public.\"userLogin\" WHERE id = '" + id + "'";
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

	public Beans getUserById(String id) {

		String query = "SELECT * FROM public.\"userLogin\" WHERE id = '" + id + "'";
		try {

			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Beans user = new Beans();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealName(rs.getString("realname"));
				user.setPhone(rs.getString("phone"));
				user.setZip(rs.getString("zip"));
				user.setStreet(rs.getString("street"));
				user.setNeighborhood(rs.getString("neighborhood"));
				user.setCity(rs.getString("city"));
				user.setState(rs.getString("state"));
				user.setIbgeCode(rs.getString("ibgecode"));
				return user;
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

		return null;
	}

	public void updateUser(Beans user) {
		String query = "UPDATE public.\"userLogin\" SET username = ? , password = ? , realname = ?, phone = ?, zip = ?, street= ?, neighborhood= ?, city= ?, state= ?, ibgecode= ? WHERE id = "
				+ user.getId();
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getRealName());
			pst.setString(4, user.getPhone());
			
			pst.setString(5, user.getZip());
			pst.setString(6, user.getStreet());
			pst.setString(7, user.getNeighborhood());
			pst.setString(8, user.getCity());
			pst.setString(9, user.getState());
			pst.setString(10, user.getIbgeCode());
			int updates = pst.executeUpdate();
			con.commit();
			if (updates > 0) {
				System.out.println("Update Successfully Completed! Number of Changes -> " + updates);
			} else {
				con.rollback();
				throw new DBException("Unexpected Error! No rows were Updated!");
			}

		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new DBException("An Error Occurred during User Update! Cause: " + e.getMessage());

			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Connection could not be Rolled Back! Cause: " + e1.getMessage());
			}
		}

	}

	public boolean validateUpdatedUser(String userName, String id) {
		// Using SQL Command "count()" to return an int value in case there are already
		// a user row with the specified username (to this, an alias (in this case "quantity"
		//is) defined to receive and manipulate this int value through .getInt("alias") method
		String query = "SELECT COUNT(1) AS quantity FROM public.\"userLogin\" WHERE username = '" + userName + "' and id <> " + id;
		try {

			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				//using alias to call out the SQL count() value
				return rs.getInt("quantity") <= 0; // Return TRUE
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

		return false;
	}
	
	public boolean validateUser(String userName) {
		// Using SQL Command "count()" to return an int value in case there are already
		// a user row with the specified username (to this, an alias (in this case "quantity"
		//is) defined to receive and manipulate this int value through .getInt("alias") method
		String query = "SELECT COUNT(1) AS quantity FROM public.\"userLogin\" WHERE username = '" + userName + "'";
		try {

			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				//using alias to call out the SQL count() value
				return rs.getInt("quantity") <= 0; // Return TRUE
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

		return false;
	}

}
