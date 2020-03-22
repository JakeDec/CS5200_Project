package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String insert = "INSERT INTO Users(UserName, SteamId) VALUES (?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, user.getUserName());
			if (user.getSteamId() == 0)
				insertStmt.setNull(2, java.sql.Types.INTEGER);
			else
				insertStmt.setInt(2,user.getSteamId());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserId(id);

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
		String delete = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);

			deleteStmt.setInt(1, user.getUserId());

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
	
	public Users getUserByUserId(int userId) throws SQLException {
		String select = "SELECT UserId,UserName,SteamId FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return new Users(
				results.getInt("UserId"),
				results.getInt("SteamId"),
				results.getString("UserName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public Users getUserBySteamId(int steamId) throws SQLException {
		String select = "SELECT * FROM Users WHERE SteamId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, steamId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return new Users(
				results.getInt("UserId"),
				results.getInt("SteamId"),
				results.getString("UserName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public Users setUserName(Users user, String userName) throws SQLException {
		String updateBlogPost = "UPDATE Users SET UserName=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogPost);
			updateStmt.setString(1, userName);
			updateStmt.setInt(2, user.getUserId());
			updateStmt.executeUpdate();

			// Update the blogPost param before returning to the caller.
			user.setUserName(userName);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}	
	
	public Users setSteamId(Users user, int steamId) throws SQLException {
		String updateBlogPost = "UPDATE Users SET SteamId=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogPost);
			updateStmt.setInt(1, steamId);
			updateStmt.setInt(2, user.getUserId());
			updateStmt.executeUpdate();

			// Update the blogPost param before returning to the caller.
			user.setSteamId(steamId);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
}
