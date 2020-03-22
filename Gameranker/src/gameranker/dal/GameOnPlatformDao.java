package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gameranker.model.GameOnPlatform;
import gameranker.model.Games;
import gameranker.model.Genres;
import gameranker.model.Platforms;
import gameranker.model.Publishers;

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
		String delete = "DELETE FROM GameOnPlatform WHERE GameIdFk = ? AND PlatformIdFk = ?;";
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
	
	public List<Platforms> getPlatformsForGame(Games game) throws SQLException {
		String select = "SELECT PlatformIdFk,GameIdFk FROM GameOnPlatform WHERE GameIdFk=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, game.getGameId());
			results = selectStmt.executeQuery();
			List<Platforms> list = new ArrayList<Platforms>();
			while(results.next()) {
				Platforms temp = PlatformsDao.getInstance().getPlatformById(results.getInt("PlatformIdFk"));
				list.add(temp);
			}
			return list;
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
	}
}
