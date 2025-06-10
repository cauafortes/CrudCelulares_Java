package controller;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebFilter(urlPatterns = { "/*" })
public class AuthFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		boolean isLoginOrLogout = uri.equals(contextPath + "/login") || uri.equals(contextPath + "/logout");
		boolean isHomePage = uri.equals(contextPath + "/") || uri.equals(contextPath + "/index.jsp");
		boolean isCelularesJSP = uri.startsWith(contextPath + "/WEB-INF/views/celulares/");
		boolean isResource = uri.startsWith(contextPath + "/css/") || uri.startsWith(contextPath + "/js/")
				|| uri.startsWith(contextPath + "/images/") || uri.startsWith(contextPath + "/resources/");

		if (isLoginOrLogout || isHomePage || isResource || isCelularesJSP) {
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = (session != null && session.getAttribute("usuarioLogado") != null);
		if (isLoggedIn) {
			Object userObject = session.getAttribute("usuarioLogado");
			if (userObject instanceof User) {
				chain.doFilter(request, response);
			} else {
				session.invalidate();
				res.sendRedirect(contextPath + "/login");
			}
		} else {
			res.sendRedirect(contextPath + "/login");
		}
	}

	@Override
	public void destroy() {
	}
}