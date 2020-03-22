package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gameranker.model.Games;
import gameranker.model.UserHasGame;
import gameranker.model.Users;

public class UserHasGameDao {
	protected ConnectionManager connectionManager;

	private static UserHasGameDao instance = null;
	protected UserHasGameDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserHasGameDao getInstance() {
		if(instance == null) {
			instance = new UserHasGameDao();
		}
		return instance;
	}

	public UserHasGame create(UserHasGame userHasGame) throws SQLException {
		String insert = "INSERT INTO UserHasGame (UserIdFk, GameIdFk, PlayTime) " + 
				"VALUES (?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);

			insertStmt.setInt(1, userHasGame.getUser().getUserId());
			insertStmt.setInt(2, userHasGame.getGame().getGameId());
			insertStmt.setFloat(3, userHasGame.getPlayTime());
			
			insertStmt.executeUpdate();

			return userHasGame;
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
	
	public List<UserHasGame> getUserHasGamesByUserId(int userId) throws SQLException {
		List<UserHasGame> userHasGames = new ArrayList<UserHasGame>();
		String select = "SELECT * FROM UserHasGame WHERE UserIdFk = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			Users user = usersDao.getUserByUserId(userId);

			while (results.next()) {
				int gameId = results.getInt("GameIdFk");
				float playTime = results.getFloat("PlayTime");

				GamesDao gamesDao = GamesDao.getInstance();
				Games game = gamesDao.getGameById(gameId);

				UserHasGame userHasGame = new UserHasGame(user, game, playTime);

				userHasGames.add(userHasGame);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (selectStmt != null) {
				selectStmt.close();
			}

			if (results != null) {
				results.close();
			}
		}

		return userHasGames;
	}
	
	public List<UserHasGame> getUserHasGamesByUserIdAndGame(int userId, int gameId) throws SQLException {
		List<UserHasGame> userHasGames = new ArrayList<UserHasGame>();
		String select = "SELECT * FROM UserHasGame WHERE UserIdFk = ? AND GameIdFk = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, userId);
			selectStmt.setInt(2, gameId);
			results = selectStmt.executeQuery();

			GamesDao gamesDao = GamesDao.getInstance();
			Games game = gamesDao.getGameById(gameId);
			
			UsersDao usersDao = UsersDao.getInstance();
			Users user = usersDao.getUserByUserId(userId);

			while (results.next()) {
				float playTime = results.getFloat("PlayTime");

				UserHasGame userHasGame = new UserHasGame(user, game, playTime);

				userHasGames.add(userHasGame);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (selectStmt != null) {
				selectStmt.close();
			}

			if (results != null) {
				results.close();
			}
		}

		return userHasGames;
	}
	
	public UserHasGame updatePlayTime(UserHasGame userHasGame, float playTime) throws SQLException {
		String update = "UPDATE UserHasGame SET playTime = ? WHERE GameIdFk = ? AND UserIdFk = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setFloat(1, userHasGame.getPlayTime());
			updateStmt.setInt(2, userHasGame.getGame().getGameId());
			updateStmt.setInt(3, userHasGame.getUser().getUserId());
			updateStmt.executeUpdate();

			userHasGame.setPlayTime(playTime);
			
			return userHasGame;
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

	public UserHasGame delete(UserHasGame userHasGame) throws SQLException {
		String delete = "DELETE FROM UserHasGame WHERE GameIdFk = ? AND UserIdFk = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);

			deleteStmt.setInt(1, userHasGame.getGame().getGameId());
			deleteStmt.setInt(2, userHasGame.getUser().getUserId());
			
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
