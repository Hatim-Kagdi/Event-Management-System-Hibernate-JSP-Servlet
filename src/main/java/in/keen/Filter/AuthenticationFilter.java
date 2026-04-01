package in.keen.Filter;

import java.io.IOException;

import in.keen.Entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.*;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		String uri = req.getRequestURI();

		boolean isPublic = uri.endsWith("login.jsp") || uri.endsWith("registerUser.jsp") || uri.contains("loginUser")
				|| uri.contains("register") || uri.contains(".css") || uri.contains(".js") || uri.contains(".png")
				|| uri.contains(".jpg");

		boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);

		if (isPublic || isLoggedIn) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}
}
