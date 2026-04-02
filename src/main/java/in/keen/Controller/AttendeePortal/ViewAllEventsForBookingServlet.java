package in.keen.Controller.AttendeePortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.EventDAO;
import in.keen.Entity.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewAllPublicEvents")
public class ViewAllEventsForBookingServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EventDAO dao = new EventDAO();
		
		List<Event> event = dao.getAllEventsForAttendee();
		
		req.setAttribute("eventList" , event);
		req.getRequestDispatcher("/Attendee/ViewAllEvents.jsp").forward(req, resp);
	}
}
