package in.keen.Controller.OrganizerCRUD;

import java.io.IOException;

import in.keen.DAO.OrganizerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteOrganizer")
public class DeleteOrganizerServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id = Integer.parseInt(req.getParameter("id"));
	
	OrganizerDAO dao = new OrganizerDAO();
	
	boolean delete = dao.deleteOrganizer(id);
	
	if(delete) {
		resp.sendRedirect(req.getContextPath() + "/ViewAllOrganizer");
	}else {
		resp.sendRedirect(req.getContextPath() + "/ViewAllOrganizer?msg=error");
	}
}
}
