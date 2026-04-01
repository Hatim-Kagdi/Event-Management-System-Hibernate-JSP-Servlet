package in.keen.Controller.OrganizerPortal;

import java.io.IOException;

import in.keen.DAO.EventDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteEvent")
public class DeleteEventServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		EventDAO dao = new EventDAO();
		
		boolean delete = dao.deleteEvent(id);
		
		if(delete) {
			resp.sendRedirect(req.getContextPath() + "/ViewAllEvents?msg=deleteSuccess");
		}else {
			resp.sendRedirect(req.getContextPath() + "/ViewAllEvents?msg=deleteFailed");
		}
	}
}
