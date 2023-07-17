package userJsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import userJsp.connection.SingleConnection;
import userJsp.model.UserModel;

public class DaoLogin {

	private Connection connection;

	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}

	public UserModel login(String username, String password) throws Exception {
		String sql = "Select * from users where user_name = '" + username + "' and password = '" + password + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("user_name"));
			user.setName(resultSet.getString("name"));
			user.setEmail(resultSet.getString("email"));
			user.setStatus(resultSet.getBoolean("status"));
			user.setsupervisorId(resultSet.getLong("supervisor_id"));
			user.setCpf(resultSet.getString("cpf"));

			return user;
		} else {
			return new UserModel();
		}
	}
}