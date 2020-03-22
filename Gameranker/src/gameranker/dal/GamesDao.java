package gameranker.dal;

import gameranker.model.Games;
import gameranker.model.Publishers;
import gameranker.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GamesDao {
	protected ConnectionManager connectionManager;

	private static GamesDao instance = null;
	protected GamesDao() {
		connectionManager = new ConnectionManager();
	}
	public static GamesDao getInstance() {
		if(instance == null) {
			instance = new GamesDao();
		}
		return instance;
	}
	
	public Games create(Games game) throws SQLException {
		String insert = "INSERT INTO Games (gameName, publisherIdFk, releaseYear) " + 
				"VALUES (?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);


			insertStmt.setString(1, game.getGameName());
			insertStmt.setInt(2, game.getPublisher().getPublisherId());
			insertStmt.setInt(3, game.getReleaseYear());
			
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int resultId = -1;

			if (resultKey.next()) {
				resultId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			game.setGameId(resultId);

			return game;
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

	public Games getGameById(int gameId) throws SQLException {
		String select = "SELECT * FROM Games WHERE GameId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String gameName = results.getString("GameName");
				int publisherId = results.getInt("PublisherIdFk");
				int releaseYear = results.getInt("ReleaseYear");
				
				PublishersDao publishersDao = PublishersDao.getInstance();
				Publishers publisher = publishersDao.getPublisherById(publisherId);

				return new Games(gameId, gameName, publisher, releaseYear);
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

		return null;
	}

	public Games delete(Games game) throws SQLException {
		String delete = "DELETE FROM Games WHERE GameId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			
			deleteStmt.setInt(1, game.getGameId());
			
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
	
	public Games setPublisher(Games game, Publishers publisher) throws SQLException {
		String updateBlogPost = "UPDATE Games SET PublisherIdFk=? WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogPost);
			updateStmt.setInt(1, publisher.getPublisherId());
			updateStmt.setInt(2, game.getGameId());
			updateStmt.executeUpdate();

			// Update the blogPost param before returning to the caller.
			game.setPublisher(publisher);
			return game;
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
	
	
	public Games setReleaseYear(Games game, int releaseYear) throws SQLException {
		String updateBlogPost = "UPDATE Games SET ReleaseYear=? WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogPost);
			updateStmt.setInt(1, releaseYear);
			updateStmt.setInt(2, game.getGameId());
			updateStmt.executeUpdate();

			// Update the blogPost param before returning to the caller.
			game.setReleaseYear(releaseYear);
			return game;
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
