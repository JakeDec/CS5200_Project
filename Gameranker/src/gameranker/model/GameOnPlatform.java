package gameranker.model;

public class GameOnPlatform {
	protected Games game;
	protected Platforms platform;
	
//	CREATE TABLE GameOnPlatform (
//			  GameIdFk INT NOT NULL,
//			  PlatformIdFk INT NOT NULL,
//			  CONSTRAINT GameOnPlatformGamesFk
//			    FOREIGN KEY (GameIdFk)
//			    REFERENCES Games (GameId)
//			    ON UPDATE CASCADE ON DELETE CASCADE,
//			  CONSTRAINT GameOnPlatformGenresFk
//			    FOREIGN KEY (PlatformIdFk)
//			    REFERENCES Platforms (PlatformId)
//			    ON UPDATE CASCADE ON DELETE CASCADE
//			) ENGINE = InnoDB;
	
	/**
	 * Construct a GameOnPlatform Object.
	 * @param game
	 * @param platform
	 */
	public GameOnPlatform(Games game, Platforms platform) {
		this.game = game;
		this.platform = platform;
	}
	
	/**
	 * Get the Game Object assigned to the GameOnPlatform Object.
	 * @return the game
	 */
	public Games getGame() {
		return this.game;
	}
	
	/**
	 * Change the Game Object assigned to the GameOnPlatform Object.
	 * @param game
	 */
	public void setGame(Games game) {
		this.game = game;
	}
	
	/**
	 * Get the Platform Object assigned to the GameOnPlatform Object.
	 * @return the platform
	 */
	public Platforms getPlatform() {
		return this.platform;
	}
	
	/**
	 * Change the Platform Object assigned to the GameOnPlatform Object.
	 * @param platform
	 */
	public void setPlatform(Platforms platform) {
		this.platform = platform;
	}
	
	public String toString() {
		return String.format("%s: %s", game.getGameName(), platform.getPlatformName());
	}
}
