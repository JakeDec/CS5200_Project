package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gameranker.model.CriticReviews;
import gameranker.model.UserReviews;

public class UserReviewsDao extends ReviewsDao{
	// Single pattern: instantiation is limited to one object.
	private static UserReviewsDao instance = null;
	protected UserReviewsDao() {
		super();
	}
	public static UserReviewsDao getInstance() {
		if(instance == null) {
			instance = new UserReviewsDao();
		}
		return instance;
	}
	
	
	public UserReviews create(UserReviews review) throws SQLException {
		super.create(review);
		String insert = "";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);
			
			insertStmt.setString(1, review.XXX());
			
			insertStmt.executeUpdate();
			
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

	public UserReviews delete(UserReviews review) throws SQLException {
		super.delete(review);
		String delete = "";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			
			deleteStmt.setString(1, review.XXX());
			
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
