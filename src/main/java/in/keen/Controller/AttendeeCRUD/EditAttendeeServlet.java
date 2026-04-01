package in.keen.Controller.AttendeeCRUD;

import java.io.IOException;

import in.keen.DAO.UserDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editAttendee")
public class EditAttendeeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		UserDAO dao = new UserDAO();
		
		User user = dao.getUserProfileDetails(id);
		
		if(user != null) {
		req.setAttribute("attendeeList", user);
		req.getRequestDispatcher("/Admin/EditAttendee.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/DashBoard/AdminDashboard.jsp");
		}
	}
}
