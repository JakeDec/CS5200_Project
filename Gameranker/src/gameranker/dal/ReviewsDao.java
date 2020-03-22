package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gameranker.model.Games;
import gameranker.model.Reviews;
import gameranker.model.Users;

public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		String insert = "INSERT INTO Reviews(GameIdFk, Review) VALUES (?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, review.getGame().getGameId());
			insertStmt.setString(2, review.getReview());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(id);

			return review;
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

	public Reviews delete(Reviews review) throws SQLException {
		String delete = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);

			deleteStmt.setInt(1, review.getReviewId());
			
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

	public List<Reviews> getReviewsByGame(Games game) throws SQLException {
		String select = "SELECT ReviewId,GameIdFk,Review FROM Reviews WHERE GameId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, game.getGameId());
			results = selectStmt.executeQuery();
			List<Reviews> list = new ArrayList<Reviews>();
			while(results.next()) {
				Reviews temp = new Reviews(results.getInt("ReviewId"),
						GamesDao.getInstance().getGameById(results.getInt("GameIdFk")),
						results.getString("Review"));
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
	
	public Reviews setReview(Reviews review, String newReview) throws SQLException {
		String updateBlogPost = "UPDATE Reviews SET Review=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogPost);
			updateStmt.setString(1, newReview);
			updateStmt.setInt(2, review.getReviewId());
			updateStmt.executeUpdate();

			review.setReview(newReview);
			return review;
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
	
	public Reviews getReviewById(int reviewId) throws SQLException {
		String select = "SELECT ReviewId,GameIdFk,Review FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return new Reviews(
				results.getInt("ReviewId"),
				GamesDao.getInstance().getGameById(results.getInt("GameIdFk")),
				results.getString("Review"));
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

}
