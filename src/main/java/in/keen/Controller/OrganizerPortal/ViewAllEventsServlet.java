package in.keen.Controller.OrganizerPortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.EventDAO;
import in.keen.Entity.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewAllEvents")
public class ViewAllEventsServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer organizerId = (Integer) req.getSession().getAttribute("userId");
		
		EventDAO dao = new EventDAO();
		
		List<Event> list = dao.getAllEvents(organizerId);

			req.setAttribute("eventList", list);
			req.getRequestDispatcher("/Organizer/ViewAllEvents.jsp").forward(req, resp);
		
	}
}
