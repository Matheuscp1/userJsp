package userJsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userJsp.dao.DaoPermission;
import userJsp.dao.DaoUser;
import userJsp.model.PermissionUser;
import userJsp.model.UserModel;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUser daoUser = new DaoUser();
	private DaoPermission daoPermission= new DaoPermission();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String user = request.getParameter("user");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Boolean status = false;
			Long supervisorId = null;
			if(request.getParameter("supervisorId") != null) {
				supervisorId  = Long.parseLong(request.getParameter("supervisorId"));
			}
	
			String cpf = request.getParameter("cpf");
			String name = request.getParameter("name");
			
			if(request.getParameter("status") != null && request.getParameter("status").equals("on")) {
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
			idUser = daoUser.save(userModel);
			String[] permissions = request.getParameterValues("permissions");
			for(String permission: permissions) {
				PermissionUser permissionUser = new PermissionUser();
				permissionUser.setPermissionId(Long.parseLong(permission.trim()));
				permissionUser.setUserId(idUser);
				daoPermission.save(permissionUser);
			}

			response.sendRedirect("home.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
