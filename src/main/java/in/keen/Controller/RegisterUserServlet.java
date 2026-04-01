package in.keen.Controller;

import java.io.IOException;

import in.keen.Entity.*;

import in.keen.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/register")
@MultipartConfig(maxFileSize = 16177215)
public class RegisterUserServlet extends HttpServlet {
	private UserDAO userdao = new UserDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		user.setUserName(req.getParameter("userName"));
		user.setUserEmail(req.getParameter("userEmail"));
		user.setUserPassword(req.getParameter("userPassword"));
		user.setUserRole(User.Role.valueOf(req.getParameter("userRole")));

		String bio = req.getParameter("userBio");
		Part filePart = req.getPart("profilePic");
		byte[] image = null;
		if (filePart != null) {
			image = filePart.getInputStream().readAllBytes();
		}

		boolean register = userdao.registerUser(user, image, bio);

		if (register) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/registerUser.jsp?msg=error");
		}
	}
}
