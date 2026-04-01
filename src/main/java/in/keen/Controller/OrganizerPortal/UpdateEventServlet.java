package in.keen.Controller.OrganizerPortal;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import in.keen.DAO.EventDAO;
import in.keen.Entity.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateEvent")
public class UpdateEventServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Event event = new Event();
	event.setEventId(Integer.parseInt(req.getParameter("eventId")));
	event.setEventName(req.getParameter("eventName"));
	event.setEventDescription(req.getParameter("eventDesp"));
	event.setEventDate(LocalDate.parse(req.getParameter("eventDate")));
	event.setEventTime(LocalTime.parse(req.getParameter("eventTime")));
	event.setEventVenue(req.getParameter("eventVenue"));
	event.setMaxCapacity(Integer.parseInt(req.getParameter("eventCapacity")));
	
	EventDAO dao = new EventDAO();
	
	boolean update = dao.updateEvent(event);
	
	if(update) {
		resp.sendRedirect(req.getContextPath() + "/ViewAllEvents?msg=success");
	}else {
		resp.sendRedirect(req.getContextPath() + "/Organizer/EditEvent.jsp?msg=error");
	}
}
}
