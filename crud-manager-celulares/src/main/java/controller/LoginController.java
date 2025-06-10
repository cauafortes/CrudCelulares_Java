package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.ModelException;
import model.dao.DAOFactory;
import model.dao.UserDAO;

@WebServlet({ "/login", "/logout" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public LoginController() {
		super();
		this.userDAO = DAOFactory.createDAO(UserDAO.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		String contextPath = req.getContextPath();
		if (action.equals(contextPath + "/login")) {
			ControllerUtil.forward(req, resp, "/login.jsp");
		} else if (action.equals(contextPath + "/logout")) {
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			ControllerUtil.redirect(resp, contextPath + "/login");
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		String contextPath = req.getContextPath();
		if (action.equals(contextPath + "/login")) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			User user = null;
			try {
				user = userDAO.findByUsernameAndPassword(email, password);
			} catch (ModelException e) {
				e.printStackTrace();
				ControllerUtil.errorMessage(req, "Erro no servidor ao tentar login.");
			}
			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("usuarioLogado", user);
				ControllerUtil.redirect(resp, contextPath + "/");
			} else {
				ControllerUtil.errorMessage(req, "Email ou senha inválidos.");
				ControllerUtil.forward(req, resp, "/login.jsp");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}