package gameranker.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gameranker.model.GameIsGenre;
import gameranker.model.Games;
import gameranker.model.Reviews;
import gameranker.model.Users;
import gameranker.model.Genres;

public class GameIsGenreDao {
	protected ConnectionManager connectionManager;

	private static GameIsGenreDao instance = null;
	protected GameIsGenreDao() {
		connectionManager = new ConnectionManager();
	}
	public static GameIsGenreDao getInstance() {
		if(instance == null) {
			instance = new GameIsGenreDao();
		}
		return instance;
	}
	
	public GameIsGenre create(GameIsGenre gameIsGenre) throws SQLException {
		String insert = "INSERT INTO GameIsGenre (GameIdFk, GenreIdFk) " + 
				"VALUES (?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);

			insertStmt.setInt(1, gameIsGenre.getGame().getGameId());
			insertStmt.setInt(2, gameIsGenre.getGenre().getGenreId());
			
			insertStmt.executeUpdate();

			return gameIsGenre;
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
	
	public GameIsGenre delete(GameIsGenre gameIsGenre) throws SQLException {
		String delete = "DELETE FROM GameIsGenre WHERE GameIdFk = ? AND GenreIdFk = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);

			deleteStmt.setInt(1, gameIsGenre.getGame().getGameId());
			deleteStmt.setInt(2, gameIsGenre.getGenre().getGenreId());
			
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
	
	public List<Genres> getGenresForGame(Games game) throws SQLException {
		String select = "SELECT GenreIdFk,GameIdFk FROM GameisGenre WHERE GameIdFk=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, game.getGameId());
			results = selectStmt.executeQuery();
			List<Genres> list = new ArrayList<Genres>();
			while(results.next()) {
				Genres temp = GenresDao.getInstance().getGenreById(results.getInt("GenreIdFk"));
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
