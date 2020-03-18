package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gameranker.model.Users;

public class UsersDao {
	protected ConnectionManager connectionManager;

	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	public Users create(Users user) throws SQLException {
		String insert = "";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);
			
			insertStmt.setString(1, user.getUserName());
			
			insertStmt.executeUpdate();
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Users delete(Users user) throws SQLException {
		String delete = "";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			
			deleteStmt.setString(1, user.getUserName());
			
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
