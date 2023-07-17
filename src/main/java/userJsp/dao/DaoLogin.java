package userJsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import userJsp.connection.SingleConnection;

public class DaoLogin {

	private Connection connection;

	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}

	public boolean validarLogin(String username, String password) throws Exception {
		String sql = "Select * from users where user_name = '" + username + 
				"' and password = '" + password + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return true;
		}else {
			return false;
		}
	}
}