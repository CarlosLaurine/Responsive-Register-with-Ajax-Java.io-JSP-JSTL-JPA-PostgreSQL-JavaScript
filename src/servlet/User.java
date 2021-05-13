package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.Beans;
import dao.userDao;
import exceptions.DBException;

@WebServlet(urlPatterns = { "/saveUser", "/deleteUser", "/editUser" })

@MultipartConfig

public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	userDao ud = new userDao();
	Beans userBeans = new Beans();

	public User() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		String fstDisplay = request.getParameter("fstDisplay");
		String reset = request.getParameter("reset");

		if (path.equals("/saveUser") && fstDisplay == null && reset == null) {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String realName = request.getParameter("realName");
			String phone = request.getParameter("phone");

			String zip = request.getParameter("zip");
			String street = request.getParameter("street");
			String neighborhood = request.getParameter("neighborhood");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String ibge = request.getParameter("ibge");

			if (id == null) {
				id = "";
			}
			userBeans.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			userBeans.setName(login);
			userBeans.setPassword(password);
			userBeans.setRealName(realName);
			userBeans.setPhone(phone);

			userBeans.setZip(zip);
			userBeans.setStreet(street);
			userBeans.setNeighborhood(neighborhood);
			userBeans.setCity(city);
			userBeans.setState(state);
			userBeans.setIbgeCode(ibge);

			try {
				// Start of File Upload of Imgs and PDF (Using .jar Files's (commons-io and
				// commons-fileupload) API Classes
				// Verifying if the receipted form is an upload-type one
				// (enctype="multipart/form-data")
				if (ServletFileUpload.isMultipartContent(request)) {
					// Setting List of Upload Items
					List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

					// Filtering Files list
					for (FileItem fileItem : fileItems) {
						// If the file is an Image
						if (fileItem.getFieldName().equals("photo")) {
							String photo = Base64.encodeBase64String(fileItem.get());
							System.out.println(photo);

						}
					}

				}
				// End of File Upload of Imgs and PDF
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			String msg = null;
			boolean validation = true;

			if (login == null || login.isEmpty()) {
				msg = "Login must be Informed!";
				validation = false;

			} else if (password == null || password.isEmpty()) {
				msg = "Password must be Informed!";
				validation = false;
			} else if (realName == null || realName.isEmpty()) {
				msg = "Name must be Informed!";
				validation = false;
			} else if (phone == null || phone.isEmpty()) {
				msg = "Phone must be Informed!";
				validation = false;
			}

			else if (id == null || id.isEmpty() && !ud.validateUser(login)) {
				msg = "System Error: Username already Exists!";
				validation = false;
			}

			else if (id == null || id.isEmpty() || id.equals("0") && ud.validateUser(login) && validation) {
				ud.save(userBeans);
				msg = "Register Successfully Saved!";
			}

			else if (id != null && !id.isEmpty() && validation) {

				if (ud.validateUpdatedUser(login, id) && !id.equals("0")) {

					ud.updateUser(userBeans);
				}

				else if (!id.equals("0")) {
					msg = "Login already in Use!";
					validation = false;
				}
			}

			// Setting Warning Alert in case Something went Wrong (if var msg != null)
			if (msg != null) {
				request.setAttribute("msg", msg);

			}

			// Redirecting user list to be displayed at userRegister

			if (validation == false) {
				request.setAttribute("selectedUser", userBeans);
			}

			try {

				RequestDispatcher display = request.getRequestDispatcher("/userRegister.jsp");
				// Setting list attribute to be sent to "/userRegister", thus being be displayed
				// by scriplets

				request.setAttribute("users", ud.listUsers());

				display.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				throw new DBException(e.getMessage());

			}

		} else if (path.equals("/deleteUser")) {
			String userDelete = request.getParameter("user");
			ud.deleteUser(userDelete);
			try {
				RequestDispatcher display = request.getRequestDispatcher("/userRegister.jsp");
				request.setAttribute("users", ud.listUsers());
				display.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				throw new DBException(e.getMessage());
			}

		} else if (path.equals("/editUser")) {
			String userEdit = request.getParameter("user");
			userBeans = ud.getUserById(userEdit);
			RequestDispatcher rd = request.getRequestDispatcher("/userRegister.jsp");
			request.setAttribute("selectedUser", userBeans);
			rd.forward(request, response);

		} else if (path.equals("/saveUser") && fstDisplay != null) {
			Beans ub = new Beans();
			RequestDispatcher rd = request.getRequestDispatcher("/userRegister.jsp");
			request.setAttribute("users", ud.listUsers());
			request.setAttribute("selectedUser", ub);
			rd.forward(request, response);

		} else if (path.equals("/saveUser") && reset != null) {

			RequestDispatcher rd = request.getRequestDispatcher("/userRegister.jsp");

			request.setAttribute("users", ud.listUsers());
			rd.forward(request, response);
		}

	}

}
