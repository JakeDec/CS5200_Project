package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gameranker.model.CriticReviews;
import gameranker.model.Games;
import gameranker.model.Publishers;
import gameranker.model.Reviews;
import gameranker.model.UserReviews;
import gameranker.model.Users;

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
	
//	CREATE TABLE UserReviews (
//	  ReviewIdFk INT NOT NULL,
//	  UserIdFk INT NOT NULL,
//	  Score FLOAT,
//	  CONSTRAINT UserReviewsReviewFk
//	    FOREIGN KEY (ReviewIdFk)
//	    REFERENCES Reviews (ReviewId)
//	    ON UPDATE CASCADE ON DELETE CASCADE,
//	  CONSTRAINT UserReviewsUsersFk
//	    FOREIGN KEY (UserIdFk)
//	    REFERENCES Users (UserId)
//	    ON UPDATE CASCADE ON DELETE CASCADE
//	) ENGINE = InnoDB;
	
	public UserReviews create(UserReviews review) throws SQLException {
		review.setReview(super.create(review.getReview()));
		String insert = "INSERT INTO UserReviews (ReviewIdFk,UserIdFk,Score) VALUES (?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);

			insertStmt.setInt(1, review.getReview().getReviewId());
			insertStmt.setInt(2, review.getUser().getUserId());
			insertStmt.setFloat(3, review.getScore());
			
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
		super.delete(review.getReview());
		return null;
	}
	
	public UserReviews getUserReviewById(int id) throws SQLException {
		String select = "SELECT ReviewIdFk,UserIdFk,Score FROM UserReviews WHERE ReviewIdFK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return new UserReviews(
				super.getReviewById(results.getInt("ReviewIdFk")),
				UsersDao.getInstance().getUserByUserId(results.getInt("UserIdFk")),
				results.getFloat("Score"));
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
	
	public UserReviews updateScore(UserReviews userReview, float score) throws SQLException {
		String update = "UPDATE UserReviews SET Score = ? WHERE ReviewIdFk = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setFloat(1, userReview.getScore());
			updateStmt.setInt(2, userReview.getReview().getReviewId());
			updateStmt.executeUpdate();

			userReview.setScore(score);
			
			return userReview;
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
	
	public List<UserReviews> getReviewsByUser(Users user) throws SQLException {
		String select = "SELECT ReviewIdFk,UserIdFk,Score FROM UserReviews WHERE UserIdFk=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, user.getUserId());
			results = selectStmt.executeQuery();
			List<UserReviews> list = new ArrayList<UserReviews>();
			while(results.next()) {
				UserReviews temp = new UserReviews(
						super.getReviewById(results.getInt("ReviewIdFk")),
						user,
						results.getFloat("Score"));
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
