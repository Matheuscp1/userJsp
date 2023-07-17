package userJsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import userJsp.connection.SingleConnection;
import userJsp.model.Permission;
import userJsp.model.UserModel;

public class DaoUser {
	private Connection connection;

	public DaoUser() {
		connection = SingleConnection.getConnection();
	}

	public Long save(UserModel user) {
		Long generatedKey = 0L;
		try {
			String sql = "insert into users (user_name, name,  email, password, status, "
					+ "cpf, supervisor_id)"
					+ "values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setBoolean(5, user.getStatus());
			statement.setString(6, user.getCpf());
			if(user.getsupervisorId() == null) {
				statement.setNull(7, 0);
			}else {
				statement.setLong(7, user.getsupervisorId());
			}
			
			statement.execute();
			connection.commit();
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
			    generatedKey =  rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
			
			}
		}
		return generatedKey;
	}
	
	public List<Permission> listAllPermissions() {
	      List<Permission> listPermission = new ArrayList<Permission>();
		try {
			String sql = "SELECT * FROM permissions";
			Statement statement = connection.createStatement();
			  ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
            	Permission permission = new Permission(rs.getLong("id"),rs.getString("name"));
            	listPermission.add(permission);
            }
		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return listPermission;
	}
	
	
	public List<UserModel> listAllUsers() {
	      List<UserModel> listUsers = new ArrayList<UserModel>();
		try {
			String sql = "SELECT a.id as id, a.user_name as user_name, a.email as email, a.cpf as cpf, a.status as status, a.name as name , b.user_name as supervisor_name"
					+ "	FROM users as a LEFT JOIN users as b on b.supervisor_id = a.id";
			Statement statement = connection.createStatement();
			  ResultSet rs = statement.executeQuery(sql);
          while (rs.next()) {
        	  UserModel user = new UserModel();
        	  user.setId(rs.getLong("id"));
        	  user.setUserName(rs.getString("user_name"));
           	  user.setName(rs.getString("name"));
           	  user.setEmail(rs.getString("email"));
           	  user.setStatus(rs.getBoolean("status"));
           	  user.setSupervisorName(rs.getString("supervisor_name"));
           	  user.setCpf(rs.getString("cpf"));
          	listUsers.add(user);
          }
		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return listUsers;
	}








}
