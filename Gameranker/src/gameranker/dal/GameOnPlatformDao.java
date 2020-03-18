package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gameranker.model.GameIsGenre;
import gameranker.model.GameOnPlatform;

public class GameOnPlatformDao {
	protected ConnectionManager connectionManager;

	private static GameOnPlatformDao instance = null;
	protected GameOnPlatformDao() {
		connectionManager = new ConnectionManager();
	}
	public static GameOnPlatformDao getInstance() {
		if(instance == null) {
			instance = new GameOnPlatformDao();
		}
		return instance;
	}
	
	
	public GameOnPlatform create(GameOnPlatform gameOnPlatform) throws SQLException {
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

	public GameOnPlatform delete(GameOnPlatform gameOnPlatform) throws SQLException {
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
