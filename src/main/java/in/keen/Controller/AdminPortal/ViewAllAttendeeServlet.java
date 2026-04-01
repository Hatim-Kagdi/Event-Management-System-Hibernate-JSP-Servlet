package in.keen.Controller.AdminPortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.UserDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewAllAttendee")
public class ViewAllAttendeeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO udao = new UserDAO();
		
		List<User> list = udao.getAllAttendee();
		
		if(list != null && !list.isEmpty()) {
			req.setAttribute("attendeeList", list);
			req.getRequestDispatcher("/Admin/ViewAllAttendee.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/DashBoard/AdminDashboard.jsp?msg=error");
		}
	}
}
