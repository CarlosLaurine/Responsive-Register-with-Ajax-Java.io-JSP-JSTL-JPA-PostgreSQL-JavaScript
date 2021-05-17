package connection;

import java.sql.Connection;
import java.sql.DriverManager;

import exceptions.DBException;

//Creating Connection in Singleton Standard

//OBS: All classes will access the same connection, without reopening it

/*OBS2: To implement the Singleton standard, a Static part is created 
  inside the class (in this case, Method connect() and, as a consequence, 
  all the required attributes for it to work. This way, the Singleton Class
  is not instantiated. Instead, it is just directly called*/

/*OBS3: This said, the connection will always be a single one, which will vary 
  will be the Connection Sessions committed at the end of each JDBC/Database operation 
  (Insertion, Reading, Update and Deletion)*/

public class SingleConnection {

	private static String driver = "org.postgresql.Driver";
	// OBS: Setting autoReconnect=true in order to ensure that, if the connection
	// fails, it will auto-reconnect itself
	private static String url = "jdbc:postgresql://localhost:5433/postgresqldb?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection con = null;

	/*
	 * Creating a static call in order to ensure that, every time SingleConnection
	 * Class is called, it will automatically execute connect() Method anyway. This
	 * way, it is possible to ensure that the Connection with the Database will be
	 * always active
	 */
	static {

		connect();

	}

	// Setting constructor with connect() static Method inside it to automatically
	// open the DB connection
	public SingleConnection() {
		connect();
	}

	// Creating private connect() method to fulfill the Connect variable "con" that
	// will be returned by public Method getConnection
	private static void connect() {
		try {

			// If connection is null when static connect() method is called, it will open a
			// brand new connection
			// Else, if there is already a Connection opened, the void Method connect will
			// just do nothing
			if (con == null) {

				Class.forName(driver);
				con = DriverManager.getConnection(url, user, password);
				con.setAutoCommit(false);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Could not connect to the Database: " + e.getMessage());
		}
	}

	// Creating public method to return Connection attribute "con" already fulfilled
	// by the automatic Static Call of Private Method connect()
	public static Connection getConnection() {
		return con;
	}

}
