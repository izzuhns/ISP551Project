package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDAO;
import model.Staff;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffDAO staffDAO;
	
	public void init() {
		staffDAO = new StaffDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
				
				
			case "/insert":
				try {
					insertStaff(request, response);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				break;
			case "/delete":
				deleteStaff(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStaff(request, response);
				break;
			default:
				// handle list
				try {
					listStaff(request,response);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listStaff(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Staff> listStaff = staffDAO.selectAllStaffs();
		request.setAttribute("listStaff", listStaff);
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Staff existingStaff = staffDAO.selectStaff(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-form.jsp");
		request.setAttribute("staff", existingStaff);
		dispatcher.forward(request, response);

	}

	private void insertStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		Staff newStaff = new Staff(name, address, phone, email, role);
		staffDAO.insertStaff(newStaff);
		response.sendRedirect("list");
	}

	private void updateStaff(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String role = request.getParameter("role");

		Staff book = new Staff(id, name, address, phone, email, role);
		staffDAO.updateStaff(book);
		response.sendRedirect("list");
	}

	private void deleteStaff(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		staffDAO.deleteStaff(id);
		response.sendRedirect("list");

	}

}
