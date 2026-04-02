package in.keen.Controller.AttendeeCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.AttendeeDAO;
import in.keen.DAO.UserDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchAttendeeForAdmin")
public class SearchAttendeeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		int pageSize = 2;
		String searchQuery = req.getParameter("searchQuery");
		List<User> list;
		AttendeeDAO dao = new AttendeeDAO();
		int totalPages;
		if (searchQuery == null || searchQuery.trim().isEmpty()) {
			list = dao.getAttendeePaginated(page, pageSize);
			Long totalRecords = dao.getAllAttendeeCount();
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		} else {
			list = dao.getAttendeeBySearchQuery(searchQuery, page, pageSize);
			Long totalRecords = dao.getSearchedAttendeeCount(searchQuery);
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		}
		req.setAttribute("attendeeList", list);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("searchQuery", searchQuery);
		req.getRequestDispatcher("/Admin/ViewAllAttendee.jsp").forward(req, resp);
	}
}
