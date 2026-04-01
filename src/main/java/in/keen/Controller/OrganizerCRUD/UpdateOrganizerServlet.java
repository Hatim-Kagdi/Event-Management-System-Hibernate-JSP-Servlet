package in.keen.Controller.OrganizerCRUD;

import java.io.IOException;

import in.keen.DAO.OrganizerDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/updateOrganizer")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateOrganizerServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("userId"));
		String name = req.getParameter("userName");
		String email = req.getParameter("userEmail");
		String bio = req.getParameter("userBio");

		User user = new User();

		user.setUserId(id);
		user.setUserName(name);
		user.setUserEmail(email);

		Part filePart = req.getPart("userProfilePicture");
		byte[] image = null;
		if (filePart != null && filePart.getSize() > 0) {
			image = filePart.getInputStream().readAllBytes();
		}

		OrganizerDAO dao = new OrganizerDAO();

		boolean update = dao.updateOrganizer(user, bio, image);

		if (update) {
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			resp.sendRedirect(req.getContextPath() + "/ViewAllOrganizer");
		} else {
			resp.sendRedirect(req.getContextPath() + "/Admin/EditOrganizer.jsp");
		}
	}
}
