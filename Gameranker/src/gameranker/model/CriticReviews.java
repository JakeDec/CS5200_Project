package gameranker.model;

public class CriticReviews {
	protected Reviews review;
	protected String criticName;
	protected float score;
	
//	CREATE TABLE CriticReviews (
//			  ReviewIdFk INT NOT NULL,
//			  CriticName VARCHAR(255),
//			  Score FLOAT,
//			  CONSTRAINT CriticReviewsReviewFk
//			    FOREIGN KEY (ReviewIdFk)
//			    REFERENCES Reviews (ReviewId)
//			    ON UPDATE CASCADE ON DELETE CASCADE
//			) ENGINE = InnoDB;
	
	/**
	 * Construct the CriticReviews Object.
	 * @param review
	 * @param criticName
	 * @param score
	 */
	public CriticReviews(Reviews review, String criticName, float score) {
		this.review = review;
		this.criticName = criticName;
		this.score = score;
	}
	
	/**
	 * Get the Review Object assigned to the CriticReviews Object.
	 * @return the review
	 */
	public Reviews getReview() {
		return this.review;
	}
	
	/**
	 * Update the review assigned to the CriticsReview Object.
	 * @param review
	 */
	public void setReview(Reviews review) {
		this.review = review;
	}
	
	/**
	 * Get the Critic Name assigned to the CriticReviews Object.
	 * @return the critic name.
	 */
	public String getCriticName() {
		return this.criticName;
	}
	public void setCriticName(String criticName) {
		this.criticName = criticName;
	}
	
	public float getScore() {
		return this.score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public String toString() {
		return String.format("%s %s %f", review.toString(), criticName, score);
	}

}
