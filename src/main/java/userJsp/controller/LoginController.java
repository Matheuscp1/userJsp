package userJsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userJsp.dao.DaoLogin;
import userJsp.dao.DaoPermission;
import userJsp.model.PermissionUser;
import userJsp.model.UserModel;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoLogin daoLogin = new DaoLogin();
	private DaoPermission daoPermission = new DaoPermission();
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
				UserModel userSearch = daoLogin.login(user, password);
				if (userSearch.getId() != null) {
					List<PermissionUser> permissions = daoPermission.permissionForUser(userSearch.getId());
					System.out.println("login" + permissions.size());
					for(PermissionUser permission: permissions) {
						userSearch.setPermissions(permissions);
					}
					page = "home.jsp";
					  HttpSession session = request.getSession();
					  session.setAttribute("user", userSearch);
					response.sendRedirect(page);
				} else {
					page = "index.jsp";
					request.setAttribute("message", msg);
					RequestDispatcher dispatcher = request.getRequestDispatcher(page);
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
