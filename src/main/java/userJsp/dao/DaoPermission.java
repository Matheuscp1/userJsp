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
			String sql = "insert IGNORE  into user_permissions (user_id, permission_id) values(?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, permissionUser.getUserId());
			statement.setLong(2, permissionUser.getPermissionId());
			statement.execute();
			connection.commit();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				generatedKey = rs.getLong(1);
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
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				PermissionUser permission = new PermissionUser();
				permission.setId(rs.getLong("id"));
				permission.setUserId(rs.getLong("user_id"));
				permission.setPermissionId(rs.getLong("permission_id"));
				permissions.add(permission);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return permissions;
	}

	public void deleteUserPermission(Long permissionId, Long userId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM user_permissions where user_id = ? AND permission_id = ?");
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, permissionId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {

			}
		}
	}

}
