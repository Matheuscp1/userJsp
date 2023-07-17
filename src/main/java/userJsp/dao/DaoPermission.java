package userJsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import userJsp.connection.SingleConnection;
import userJsp.model.PermissionUser;
import userJsp.model.UserModel;

public class DaoPermission {
	private Connection connection;

	public DaoPermission() {
		connection = SingleConnection.getConnection();
	}

	public Long save(PermissionUser permissionUser) {
		Long generatedKey = 0L;
		try {
			String sql = "insert into user_permissions (user_id, permission_id) values(?,?)";
			PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			statement.setLong(1, permissionUser.getUserId());
			statement.setLong(2, permissionUser.getPermissionId());
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
	
	public List<PermissionUser> permissionForUser(Long id) throws Exception {
		String sql = "SELECT * FROM user_permissions WHERE user_id = " + id;
		List<PermissionUser> permissions = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			PermissionUser permission = new PermissionUser();
			permission.setId(resultSet.getLong("id"));
			permission.setUserId(resultSet.getLong("user_id"));
			permission.setPermissionId(resultSet.getLong("permission_id"));


			permissions.add(permission);
		} 
		return permissions;
	}
}