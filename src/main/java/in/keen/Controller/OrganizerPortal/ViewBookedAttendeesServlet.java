package in.keen.Controller.OrganizerPortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.BookingDAO;
import in.keen.Entity.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewBookedAttendees")
public class ViewBookedAttendeesServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int eventId = Integer.parseInt(req.getParameter("id"));
		
		BookingDAO dao = new BookingDAO();
		
		List<Booking> list = dao.getBookingForEvent(eventId);
		
		req.setAttribute("bookingAttendeeList", list);
		req.getRequestDispatcher("/Organizer/ViewBookedAttendee.jsp").forward(req, resp);
		
	}
}
