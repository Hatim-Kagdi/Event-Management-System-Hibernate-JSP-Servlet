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

@WebServlet("/ViewAllOrganizer")
public class ViewAllOrganizerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		int pageSize = 2;
		OrganizerDAO dao = new OrganizerDAO();
		
		List<User> list = dao.getOrganizerPagination(page, pageSize);
		Long totalRecords =  dao.getAllOrganizerCount();
		int totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		
			
			req.setAttribute("organizerList", list);
			req.setAttribute("currentPage", page);
			req.setAttribute("totalPages", totalPages);
			req.getRequestDispatcher("/Admin/ViewAllOrganizer.jsp").forward(req, resp);
	}
}
