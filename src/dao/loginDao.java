package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import exceptions.DBException;

public class loginDao {
	
	private Connection con;

	
	public loginDao() {
		con = SingleConnection.getConnection();
	}
	
	public boolean validateLogin(String name, String password) {
		String query = "select * from public.\"userLogin\" WHERE username = ? and password = ?";
		
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			con.commit();
			
			if (rs.next()) {
				return true; //The informed login exists at the DB
			}
	
		} catch (SQLException e) {
			try {
				con.rollback();				
				e.printStackTrace();
				throw new DBException(e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DBException(e.getMessage());
			}
			
		}
		return false; //The informed login does not exist at the DB
	}
}
