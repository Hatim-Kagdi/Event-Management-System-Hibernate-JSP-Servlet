package in.keen.Controller.AttendeePortal;

import java.io.IOException;

import in.keen.DAO.BookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CancelBooking")
public class CancelBookingServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookingId = Integer.parseInt(req.getParameter("id"));
		
		BookingDAO dao = new BookingDAO();
		
		boolean cancel = dao.cancelBooking(bookingId);
		
		if(cancel) {
			resp.sendRedirect(req.getContextPath() + "/ViewMyBookings?msg=success");
		}else {
			resp.sendRedirect(req.getContextPath() + "/Attendee/ViewMyBookings.jsp?msg=cancel_failed");
		}
	}
}
