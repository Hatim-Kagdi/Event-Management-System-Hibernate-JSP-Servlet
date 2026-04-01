package in.keen.Controller.AttendeeCRUD;

import java.io.IOException;

import in.keen.DAO.AttendeeDAO;
import in.keen.DAO.UserDAO;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/updateAttendee")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateAttendeeServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		int userId = Integer.parseInt(req.getParameter("userId"));
		String userName = req.getParameter("userName");
		String userEmail = req.getParameter("userEmail");
		String userBio = req.getParameter("userBio");
		Part filePart = req.getPart("userProfilePicture");
		byte[] image = null;
		if(filePart != null && filePart.getSize() > 0) {
			image = filePart.getInputStream().readAllBytes();
		}
		
		System.out.println("Image bytes: " + (image != null ? image.length : "null"));
		
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserEmail(userEmail);

		AttendeeDAO dao = new AttendeeDAO();
		boolean update = dao.updateAttendee(user, userBio, image);
		
		if(update) {
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			resp.sendRedirect(req.getContextPath() + "/ViewAllAttendee");
		}else {
			resp.sendRedirect(req.getContextPath() + "/Admin/EditAttendee.jsp");
		}
 	}
}
