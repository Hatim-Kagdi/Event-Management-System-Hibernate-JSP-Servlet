package in.keen.Controller.OrganizerPortal;

import java.io.IOException;

import in.keen.DAO.EventDAO;
import in.keen.Entity.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editEvent")
public class EditEventServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		EventDAO dao = new EventDAO();
		
		Event event = dao.getEventById(id);
		
		if(event != null) {
			req.setAttribute("eventDetails", event);
			req.getRequestDispatcher("/Organizer/EditEvent.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/Organizer/OrganizerDashboard.jsp");
		}
	}
}
