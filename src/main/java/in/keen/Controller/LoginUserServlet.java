package in.keen.Controller;

import java.io.IOException;

import in.keen.DAO.UserDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginUser")
public class LoginUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("loginEmail");
		String password = req.getParameter("loginPassword");

		UserDAO udao = new UserDAO();

		User user = udao.loginUser(email, password);

		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userRole", user.getUserRole().name());
			
			if (user.getUserRole() == User.Role.ADMIN) {
	            resp.sendRedirect(req.getContextPath() + "/DashBoard/AdminDashboard.jsp");
	        } 
	        else if (user.getUserRole() == User.Role.ORGANIZER) {
	            resp.sendRedirect(req.getContextPath() + "/DashBoard/OrganizerDashboard.jsp");
	        } 
	        else {
	            resp.sendRedirect(req.getContextPath() + "/DashBoard/AttendeeDashboard.jsp");
	        }
			
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp?error=1");
		}
	}
}
