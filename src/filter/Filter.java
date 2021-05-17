package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;
import exceptions.DBException;

//Implementing interface Filter from javax.servlet and its methods

/*Mapping filter with annotation @WebFilter and instancing all url 
  (Requisitions/Responses) Patterns that will be intercepted by 
  Filter (in this case,  all of them. Syntax -> /* )*/

@WebFilter(urlPatterns = { "/*" })
public class Filter implements javax.servlet.Filter {

	// Creating static Connection object in order to allow Filter to receive the
	// initiated connection at init() and treat it at doFilter
	private static Connection con;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		/*
		 * Using FilterChain object arg2 to call method doFilter() passing the Request
		 * and Response objects as parameters to be interpreted by the Filter Chain
		 */
		try {

			arg2.doFilter(arg0, arg1);

			/*
			 * In case everything went right past through doFilter, then the Connection will
			 * initiate
			 */

			con.commit();

		} catch (Exception e) {

			try {
				e.printStackTrace();
				con.rollback();
			} catch (SQLException e1) {
				throw new DBException("Connection could not be rolled back! Cause: " + e.getMessage());
			}

		}

	}

	// Init is the first method to run once Filter is first Activated
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		con = SingleConnection.getConnection();
	}

}
