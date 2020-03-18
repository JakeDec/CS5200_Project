package gameranker.model;

public class UserHasGame {
	private Users user;
	private Games game;
	private int playTime;
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public Games getGame() {
		return game;
	}
	public void setGame(Games game) {
		this.game = game;
	}
	
	public int getPlayTime() {
		return playTime;
	}
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
}
