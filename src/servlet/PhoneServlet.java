package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Beans;
import beans.Phone;
import dao.phoneDao;
import dao.userDao;

@WebServlet(urlPatterns = { "/savePhone", "/redirect", "/deletePhone" })
public class PhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDao ud = new userDao();
	private Beans userBeans = new Beans();
	private phoneDao pd = new phoneDao();
	private Phone phone = new Phone();

	public PhoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action.equals("addPhone")) {
			String id = request.getParameter("user");
			userBeans = ud.getUserById(id);
			// Setting attribute to be stored in Session in order to be reloaded later
			request.getSession().setAttribute("selectedUser", userBeans);

			RequestDispatcher rd = request.getRequestDispatcher("phoneRegister.jsp");
			// Setting attribute to be dispatched to phoneRegister (since setting it at the
			// session has no effect in this case)
			request.setAttribute("selectedUser", userBeans);
			// Return Phone List to be displayed at the Phone Page since the First Log
			request.setAttribute("phones", pd.listPhone(userBeans.getId()));
			rd.forward(request, response);
		} else if (action.equals("deletePhone")) {
			String phoneId = request.getParameter("phoneId");
			pd.deletePhone(phoneId);

			Beans ub = (Beans) request.getSession().getAttribute("selectedUser");

			RequestDispatcher rd = request.getRequestDispatcher("phoneRegister.jsp");
			// Setting attribute to be dispatched to phoneRegister (since setting it at the
			// session has no effect in this case)
			request.setAttribute("selectedUser", userBeans);
			request.setAttribute("msg", "Phone Sucessfully Removed!");
			// Return Phone List to be displayed at the Phone Page since the First Log
			request.setAttribute("phones", pd.listPhone(ub.getId()));
			rd.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();

		System.out.println();
		
		// Catching Selected User Attribute (set at doGet) from Session Scope
		Beans ub1 = (Beans) request.getSession().getAttribute("selectedUser");
		String number = request.getParameter("number");
		String type = request.getParameter("type");

		phone.setNumber(number);
		phone.setType(type);
		// Setting Phone/User Relation through catch Attribute from Session
		phone.setUserRegister(ub1.getId());

		pd.save(phone);

		request.getSession().setAttribute("selectedUser", ub1);
		request.setAttribute("selectedUser", ub1);

		RequestDispatcher rd = request.getRequestDispatcher("phoneRegister.jsp");
		// Filtering the list of all Phones related to the Specific User and sending
		// them Straight to phoneRegister View Layer Class as an Attribute
		request.setAttribute("phones", pd.listPhone(ub1.getId()));
		request.setAttribute("msg", "Phone Successfully Added");
		rd.forward(request, response);
	}

}
