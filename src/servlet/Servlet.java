package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.loginDao;

@WebServlet(urlPatterns = { "/login" })
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private loginDao dl = new loginDao();

	public Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (dl.validateLogin(request.getParameter("name"), request.getParameter("password"))) {
			response.sendRedirect("accessPermitted.jsp");
		} else {
			String message = "Access Denied! Please Re-Insert your Login and Password!";
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("denied", message);
			rd.forward(request, response);
		}
	}

}
