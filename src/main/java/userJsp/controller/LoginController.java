package userJsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userJsp.dao.DaoLogin;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoLogin daoLogin = new DaoLogin();

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String msg = "Senha ou usuário inválido";
		String page  = "index.jsp";
		try {
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {

				if (daoLogin.validarLogin(user, password)) {
					page = "home.jsp";
				} else {
					page = "index.jsp";
					request.setAttribute("message", msg);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
