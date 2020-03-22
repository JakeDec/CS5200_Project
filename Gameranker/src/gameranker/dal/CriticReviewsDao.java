package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gameranker.model.CriticReviews;
import gameranker.model.UserReviews;
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
//	CREATE TABLE CriticReviews (
//	  ReviewIdFk INT NOT NULL,
//	  CriticName VARCHAR(255),
//	  Score FLOAT,
//	  CONSTRAINT CriticReviewsReviewFk
//	    FOREIGN KEY (ReviewIdFk)
//	    REFERENCES Reviews (ReviewId)
//	    ON UPDATE CASCADE ON DELETE CASCADE
//	) ENGINE = InnoDB;
	
	public CriticReviews create(CriticReviews review) throws SQLException {
		review.setReview(super.create(review.getReview()));
		String insert = "INSERT INTO CriticReviews (ReviewIdFk,CriticName,Score) VALUES (?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);

			insertStmt.setInt(1, review.getReview().getReviewId());
			insertStmt.setString(2, review.getCriticName());
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

	public CriticReviews delete(CriticReviews review) throws SQLException {
		super.delete(review.getReview());
		return null;
	}
	
	public CriticReviews getCriticReviewById(int id) throws SQLException {
		String select = "SELECT ReviewIdFk,CriticName,Score FROM CriticReviews WHERE ReviewIdFK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return new CriticReviews(
				super.getReviewById(results.getInt("ReviewIdFk")),
				results.getString("CriticName"),
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
	
	public List<CriticReviews> getReviewsByCriticName(String criticName) throws SQLException {
		String select = "SELECT ReviewIdFk,CriticName,Score FROM CriticReviews WHERE CriticName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, criticName);
			results = selectStmt.executeQuery();
			List<CriticReviews> list = new ArrayList<CriticReviews>();
			while(results.next()) {
				CriticReviews temp = new CriticReviews(
						super.getReviewById(results.getInt("ReviewIdFk")),
						criticName,
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
