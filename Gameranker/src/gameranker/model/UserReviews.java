package gameranker.model;

public class UserReviews {
	protected Reviews review;
	protected Users user;
	protected float score;
	
//	CREATE TABLE UserReviews (
//			  ReviewIdFk INT NOT NULL,
//			  UserIdFk INT NOT NULL,
//			  Score FLOAT,
//			  CONSTRAINT UserReviewsReviewFk
//			    FOREIGN KEY (ReviewIdFk)
//			    REFERENCES Reviews (ReviewId)
//			    ON UPDATE CASCADE ON DELETE CASCADE,
//			  CONSTRAINT UserReviewsUsersFk
//			    FOREIGN KEY (UserIdFk)
//			    REFERENCES Users (UserId)
//			    ON UPDATE CASCADE ON DELETE CASCADE
//			) ENGINE = InnoDB;
	
	/**
	 * Construct a UserReviews Object.
	 * @param review
	 * @param user
	 * @param score
	 */
	public UserReviews(Reviews review, Users user, float score) {
		this.review = review;
		this.user = user;
		this.score = score;
	}
	
	/**
	 * Get the Review object from the UserReviews Object.
	 * @return the Review Object.
	 */
	public Reviews getReview() {
		return this.review;
	}
	
	/**
	 * Change the Review object assigned to the UserReview Object.
	 * @param review
	 */
	public void setReview(Reviews review) {
		this.review = review;
	}
	
	/**
	 * Get the user from the UserReviews object.
	 * @return the User object.
	 */
	public Users getUser() {
		return this.user;
	}
	/**
	 * Change the User object assigned to the UserReviews object.
	 * @param user
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
	/**
	 * Get the score from the UserReviews object.
	 * @return the User score.
	 */
	public float getScore() {
		return this.score;
	}
	
	/**
	 * Change the User Score.
	 * @param score
	 */
	public void setScore(float score) {
		this.score = score;
	}

}
