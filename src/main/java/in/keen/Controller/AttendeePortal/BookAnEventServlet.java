package in.keen.Controller.AttendeePortal;

import java.io.IOException;

import in.keen.DAO.BookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BookEvent")
public class BookAnEventServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("userId") == null) {
			resp.sendRedirect(req.getContextPath()+ "/login.jsp?msg=sessionExpired");
		}
		
		int eventId = Integer.parseInt(req.getParameter("eventId"));
		int attendeeId = (Integer) session.getAttribute("userId");
		
		BookingDAO dao = new BookingDAO();
		String result = dao.createBooking(attendeeId, eventId);
		
		if("SUCCESS" .equals(result)) {
			resp.sendRedirect(req.getContextPath() + "/ViewAllPublicEvents?msg=success");
		}else if("FULL".equals(result)) {
			resp.sendRedirect(req.getContextPath() + "/ViewAllPublicEvents?msg=full");
		}else {
			resp.sendRedirect(req.getContextPath() + "/ViewAllPublicEvents?msg=error");
		}
		
	}
}
