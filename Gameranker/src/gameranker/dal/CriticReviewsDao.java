package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gameranker.model.CriticReviews;
import gameranker.model.Users;

public class CriticReviewsDao extends ReviewsDao{
	// Single pattern: instantiation is limited to one object.
	private static CriticReviewsDao instance = null;
	protected CriticReviewsDao() {
		super();
	}
	public static CriticReviewsDao getInstance() {
		if(instance == null) {
			instance = new CriticReviewsDao();
		}
		return instance;
	}
	
	public CriticReviews create(CriticReviews review) throws SQLException {
		super.create(review.getReview());
		String insert = "";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);
			
			insertStmt.setString(1, "");
			
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

	public CriticReviews delete(CriticReviews review) throws SQLException {
		super.delete(review.getReview());
		String delete = "";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);

			deleteStmt.setString(1, "");
			
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
