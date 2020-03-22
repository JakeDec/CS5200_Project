package gameranker.model;

public class UserHasGame {
	private Users user;
	private Games game;
	private float playTime;
	
//	CREATE TABLE UserHasGame (
//			  UserIdFk INT NOT NULL,
//			  GameIdFk INT NOT NULL,
//			  PlayTime FLOAT,
//			  CONSTRAINT UserHasGamePk
//			    UNIQUE (UserIdFk, GameIdFk),
//			  CONSTRAINT UserHasGameUsersFk
//			    FOREIGN KEY (UserIdFk)
//			    REFERENCES Users (UserId)
//			    ON UPDATE CASCADE ON DELETE CASCADE,
//			  CONSTRAINT UserHasGameGamesFk
//			    FOREIGN KEY (GameIdFk)
//			    REFERENCES Games (GameId)
//			    ON UPDATE CASCADE ON DELETE CASCADE
//			) ENGINE = InnoDB;
	
	/**
	 * Construct a UserHasGame Object with their playtime known.
	 * @param user
	 * @param game
	 * @param playTime
	 */
	public UserHasGame(Users user, Games game, float playTime) {
		this.user = user;
		this.game = game;
		this.playTime = playTime;
	}
	
	/**
	 * Construct a UserHasGame Object without a playtime defined.
	 * playTime = NULL.
	 * @param user
	 * @param game
	 */
	public UserHasGame(Users user, Games game) {
		this.user = user;
		this.game = game;
	}
	
	/**
	 * Get the User Object assigned to the UserHasGame Object.
	 * @return the user.
	 */
	public Users getUser() {
		return this.user;
	}
	
	/**
	 * Change the User Object assigned to the UserHasGame Object.
	 * @param user
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
	/**
	 * Get the Game Object assigned to the UserHasGame Object.
	 * @return the game.
	 */
	public Games getGame() {
		return this.game;
	}
	
	/**
	 * Change the Game Object assigned to the UserHasGame Object.
	 * @param game
	 */
	public void setGame(Games game) {
		this.game = game;
	}
	
	/**
	 * Get the play time from the UserHasGame Object.
	 * @return the play time.
	 */
	public float getPlayTime() {
		return this.playTime;
	}
	
	/**
	 * Change the play time assigned to the UserHasGame Object.
	 * @param playTime
	 */
	public void setPlayTime(float playTime) {
		this.playTime = playTime;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s %d", user.getUserName(), game.getGameName(), playTime);
	}
}
