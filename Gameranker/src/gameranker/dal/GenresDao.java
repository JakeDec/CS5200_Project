package gameranker.dal;

import gameranker.model.Genres;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenresDao {
	protected ConnectionManager connectionManager;

	private static GenresDao instance = null;
	protected GenresDao() {
		connectionManager = new ConnectionManager();
	}
	public static GenresDao getInstance() {
		if(instance == null) {
			instance = new GenresDao();
		}
		return instance;
	}
	
	public Genres create(Genres genre) throws SQLException {
		String insert = "INSERT INTO Genres (genre) " + 
				"VALUES (?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, genre.getGenre());
			
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int resultId = -1;

			if (resultKey.next()) {
				resultId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			genre.setGenreId(resultId);

			return genre;
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

	public Genres getGenreById(int genreId) throws SQLException {
		String selectReview = "SELECT * FROM Genres WHERE GenreId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, genreId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String genre = results.getString("Genre");

				return new Genres(genreId, genre);
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

	public Genres delete(Genres genre) throws SQLException {
		String delete = "DELETE FROM Genres WHERE GenreId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			
			deleteStmt.setInt(1, genre.getGenreId());
			
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
