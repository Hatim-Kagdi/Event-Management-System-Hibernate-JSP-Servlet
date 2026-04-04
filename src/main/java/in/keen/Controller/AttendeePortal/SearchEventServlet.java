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

@WebServlet("/SearchEvent")
public class SearchEventServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchQuery = req.getParameter("searchQuery");
		
		EventDAO dao = new EventDAO();
		
		List<Object[]> list = dao.getSearchedEvents(searchQuery);
		
		req.setAttribute("eventList", list);
		req.getRequestDispatcher("/Attendee/ViewAllEvents.jsp").forward(req, resp);;
	}
}
