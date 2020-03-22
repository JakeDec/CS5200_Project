package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		String insert = "INSERT INTO GameOnPlatform (GameIdFk, PlatformIdFk) " + 
				"VALUES (?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);

			insertStmt.setInt(1, gameOnPlatform.getGame().getGameId());
			insertStmt.setInt(2, gameOnPlatform.getPlatform().getPlatformId());
			
			insertStmt.executeUpdate();

			return gameOnPlatform;
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
		String delete = "DELETE FROM GameOnPlatform WHERE GameIdFk = ? AND PlatformIdFk = ;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);

			deleteStmt.setInt(1, gameOnPlatform.getGame().getGameId());
			deleteStmt.setInt(2, gameOnPlatform.getPlatform().getPlatformId());
			
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
