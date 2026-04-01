package in.keen.Controller.OrganizerCRUD;

import java.io.IOException;

import java.util.List;

import in.keen.DAO.OrganizerDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewAllOrganizer")
public class ViewAllOrganizerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrganizerDAO dao = new OrganizerDAO();
		
		List<User> list = dao.getAllOrganizer();
		
		if(list != null && !list.isEmpty()) {
			req.setAttribute("organizerList", list);
			req.getRequestDispatcher("/Admin/ViewAllOrganizer.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/DashBoard/AdminDashboard.jsp");
		}
	}
}
