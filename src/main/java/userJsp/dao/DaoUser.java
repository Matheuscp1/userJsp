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

	public void save(UserModel user) {
		try {
			String sql = "insert into users (user_name, name,  email, password, status, "
					+ "cpf, supervisor_id)"
					+ "values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, user.getUserName());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setBoolean(5, user.getStatus());
			statement.setString(6, user.getCpf());
			statement.setNull(7, 0);
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

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
}
