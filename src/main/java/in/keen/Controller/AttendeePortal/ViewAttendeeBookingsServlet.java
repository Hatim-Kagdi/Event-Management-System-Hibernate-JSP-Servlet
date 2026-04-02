package in.keen.Controller.AttendeePortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.BookingDAO;
import in.keen.Entity.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewMyBookings")
public class ViewAttendeeBookingsServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute("userId") == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
		
		int attendeeId = (Integer) session.getAttribute("userId");
		
		BookingDAO dao = new BookingDAO();
		
		List<Booking> list = dao.getBookingByAttendeeId(attendeeId);
		
		req.setAttribute("bookingList", list);
		req.getRequestDispatcher("/Attendee/ViewMyBookings.jsp").forward(req, resp);
	}
}
