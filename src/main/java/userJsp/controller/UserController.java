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

import userJsp.dao.DaoPermission;
import userJsp.dao.DaoUser;
import userJsp.model.PermissionUser;
import userJsp.model.UserModel;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "Usuarios", urlPatterns = { "/UserController" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUser daoUser = new DaoUser();
	private DaoPermission daoPermission = new DaoPermission();

	private static String INSERT_OR_EDIT = "user.jsp";
	private static String LIST_USER = "home.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("delete")) {
				Long userId = Long.parseLong(request.getParameter("userId"));
				daoUser.deleteUser(userId);
				response.sendRedirect(LIST_USER);
			} else if (action.equalsIgnoreCase("edit")) {
				Long id = Long.parseLong(request.getParameter("userId").trim());
				UserModel userSearch = daoUser.findById(id);
				if (userSearch.getId() != null) {
					List<PermissionUser> permissions = daoPermission.permissionForUser(userSearch.getId());
					for (PermissionUser permission : permissions) {
						userSearch.setPermissions(permissions);
					}
					request.setAttribute("userEdit", userSearch);
					RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
					dispatcher.forward(request, response);
				}
			}else if(action.equalsIgnoreCase("logout")) {
				 HttpSession session = request.getSession();
				 session.removeAttribute("user");
				 response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String user = request.getParameter("user");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Boolean status = false;
			Long supervisorId = null;
			if (request.getParameter("supervisorId") != null && !request.getParameter("supervisorId").isEmpty()) {
				supervisorId = Long.parseLong(request.getParameter("supervisorId").trim());
			}

			String cpf = request.getParameter("cpf");
			String name = request.getParameter("name");

			if (request.getParameter("status") != null && request.getParameter("status").equals("on")) {
				status = true;
			}

			UserModel userModel = new UserModel();
			userModel.setUserName(user);
			userModel.setEmail(email);
			userModel.setPassword(password);
			userModel.setStatus(status);
			userModel.setsupervisorId(supervisorId);
			userModel.setCpf(cpf);
			userModel.setName(name);
			Long idUser = 0L;
			//System.out.println(userModel.getsupervisorId());

			if (request.getParameter("userId") == "") {
				idUser = daoUser.save(userModel);
				String[] permissions = request.getParameterValues("permissions");
				for (String permission : permissions) {
					PermissionUser permissionUser = new PermissionUser();
					permissionUser.setPermissionId(Long.parseLong(permission.trim()));
					permissionUser.setUserId(idUser);
					daoPermission.save(permissionUser);
				}

				response.sendRedirect("home.jsp");
			} else {
				userModel.setId(Long.parseLong(request.getParameter("userId")));
				daoUser.updateUser(userModel);
				if(request.getParameter("removePermission") != null && !request.getParameter("removePermission").isEmpty()) {
					Long removePermission = Long.parseLong(request.getParameter("removePermission"));
					daoPermission.deleteUserPermission(removePermission, userModel.getId());
				}
				String[] permissions = request.getParameterValues("permissions");
				for (String permission : permissions) {
					PermissionUser permissionUser = new PermissionUser();
					permissionUser.setPermissionId(Long.parseLong(permission.trim()));
					permissionUser.setUserId(userModel.getId());
					daoPermission.save(permissionUser);
				}
				response.sendRedirect("home.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
