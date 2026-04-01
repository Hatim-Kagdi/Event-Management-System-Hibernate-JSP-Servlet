package in.keen.Controller.AttendeeCRUD;

import java.io.IOException;

import in.keen.DAO.AttendeeDAO;
import in.keen.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteAttendee")
public class DeleteAttendeeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		AttendeeDAO dao = new AttendeeDAO();
		
		boolean delete = dao.deleteAttendee(id);
		
		if(delete) {
			resp.sendRedirect(req.getContextPath() + "/ViewAllAttendee");
		}else {
			resp.sendRedirect(req.getContextPath() + "/ViewAllAttendee");
		}
	}
}
