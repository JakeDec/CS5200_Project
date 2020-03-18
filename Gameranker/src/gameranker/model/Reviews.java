package gameranker.model;

public class Reviews {
	private int reviewId;
	private Games game;
	private String review;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Games getGame() {
		return game;
	}
	public void setGame(Games game) {
		this.game = game;
	}
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

}
