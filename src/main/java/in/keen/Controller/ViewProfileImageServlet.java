package in.keen.Controller;

import java.io.IOException;

import in.keen.Entity.Profile;
import in.keen.Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewProfilePicture")
public class ViewProfileImageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		User user = (User) s.getAttribute("session_user");
		
		byte[] image = user.getProfile().getProfilePicture();
		
		if(image != null) {
			resp.setContentType("image/jpeg");
			resp.getOutputStream().write(image);
		}
		
	}
}
