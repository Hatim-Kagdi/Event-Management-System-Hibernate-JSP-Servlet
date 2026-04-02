package in.keen.Controller.OrganizerCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.OrganizerDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchOrganizerForAdmin")
public class SearchOrganizerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String searchQuery = req.getParameter("searchQuery");
		
		int page = 1;
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		int pageSize = 2;
		
		OrganizerDAO dao = new OrganizerDAO();
		List<User> list;
		int totalPages;
		if(searchQuery == null || searchQuery.trim().isEmpty()) {
			list = dao.getOrganizerPagination(page, pageSize);
			Long totalRecords = dao.getAllOrganizerCount();
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		}else {
			list = dao.getSearchedOrganizer(searchQuery, page, pageSize);
			Long totalRecords = dao.getSearchedOrganizerCount(searchQuery);
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		}
		
		req.setAttribute("organizerList", list);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("searchQuery", searchQuery);
		req.getRequestDispatcher("/Admin/ViewAllOrganizer.jsp").forward(req, resp);
	}
}
