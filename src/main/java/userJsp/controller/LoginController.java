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
		
		try {

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			/*
			 * if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			 * 
			 * if (daoLogin.validarLogin(login, senha)) { RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("acessoliberado.jsp");
			 * dispatcher.forward(request, response); } else { RequestDispatcher dispatcher
			 * = request.getRequestDispatcher("acessonegado.jsp");
			 * dispatcher.forward(request, response); } } else { RequestDispatcher
			 * dispatcher = request.getRequestDispatcher("index.jsp");
			 * dispatcher.forward(request, response); }
			 */
			
			System.out.println(login + " " + senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
