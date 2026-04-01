package in.keen.Controller.OrganizerPortal;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import in.keen.DAO.EventDAO;
import in.keen.Entity.Event;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createNewEvent")
public class CreateNewEventServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer organizerId = (Integer) req.getSession().getAttribute("userId");
		
		Event event = new Event();
		event.setEventName(req.getParameter("eventName"));
		event.setEventDescription(req.getParameter("eventDesp"));
		event.setEventDate(LocalDate.parse(req.getParameter("eventDate")));
		event.setEventTime(LocalTime.parse(req.getParameter("eventTime")));
		event.setEventVenue(req.getParameter("eventVenue"));
		event.setMaxCapacity(Integer.parseInt(req.getParameter("eventCapacity")));
		
		EventDAO dao = new EventDAO();
		
		boolean success = dao.createNewEvent(event, organizerId);
		
		if(success) {
			resp.sendRedirect(req.getContextPath() + "/DashBoard/OrganizerDashboard.jsp?msg=success");
		}else {
			resp.sendRedirect(req.getContextPath() + "/Organizer/AddNewEvent.jsp?msg=error");
		}
	}
}
