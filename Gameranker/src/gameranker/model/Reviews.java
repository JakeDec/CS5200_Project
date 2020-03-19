package gameranker.model;

public class Reviews {
	protected int reviewId;
	protected Games game;
	protected String review;
	
//	CREATE TABLE Reviews (
//			  ReviewId INT NOT NULL UNIQUE AUTO_INCREMENT,
//			  GameIdFK INT NOT NULL,
//			  Review TEXT,
//			  CONSTRAINT ReviewsGamesFK
//			    FOREIGN KEY (GameIdFk)
//			    REFERENCES Games (GameId)
//			    ON UPDATE CASCADE ON DELETE CASCADE
//			) ENGINE = InnoDB;

	/**
	 * Construct a Reviews Object with the review known.
	 * @param reviewId
	 * @param game
	 * @param review
	 */
	public Reviews(int reviewId, Games game, String review) {
		this.reviewId = reviewId;
		this.game = game;
		this.review = review;
	}
	
	/**
	 * Construct a Reviews Object without a review assigned. 
	 * @param reviewId
	 * @param game
	 */
	public Reviews(int reviewId, Games game) {
		this.reviewId = reviewId;
		this.game = game;
	}
	
	
	/**
	 * Get the review id of the Reviews Object.
	 * @return the id.
	 */
	public int getReviewId() {
		return this.reviewId;
	}
	
	/**
	 * Change the review id of the Reviews object.
	 * @param reviewId
	 */
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	/**
	 * Get the Game Object assigned to the Reviews Object.
	 * @return the game
	 */
	public Games getGame() {
		return this.game;
	}
	
	/**
	 * Change the Game Object assigned to the Reviews Object.
	 * @param game
	 */
	public void setGame(Games game) {
		this.game = game;
	}
	
	/**
	 * Get the review from the Reviews Object.
	 * @return the review.
	 */
	public String getReview() {
		return this.review;
	}
	
	/**
	 * Update the review for the Reviews Object.
	 * @param review
	 */
	public void setReview(String review) {
		this.review = review;
	}

}
