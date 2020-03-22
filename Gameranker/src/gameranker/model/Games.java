package gameranker.model;

public class Games {
	protected int gameId;
	protected String gameName;
	protected Publishers publisher;
	protected int releaseYear;

	//	CREATE TABLE Games (
	//		GameId INT NOT NULL UNIQUE AUTO_INCREMENT,
	//		GameName VARCHAR(255) NOT NULL UNIQUE,
	// 		PublisherIdFk INT,
	//		ReleaseYear INT,
	//		CONSTRAINT GamesPk
	//			PRIMARY KEY (GameId),
	//		CONSTRAINT GamesPublishersFk
	//			FOREIGN KEY (PublisherIdFk)
	//			REFERENCES Publishers (PublisherId)
	//			ON UPDATE CASCADE ON DELETE SET NULL
	//	) ENGINE = InnoDB;

	/**
	 * Construct a game object.
	 * @param gameId
	 * @param gameName
	 * @param publisher
	 * @param releaseYear
	 */
	public Games(int gameId, String gameName, Publishers publisher, int releaseYear) {
		this.gameId = gameId;
		this.gameName = gameName;
		this.publisher = publisher;
		this.releaseYear = releaseYear;
	}
	
	/**
	 * Construct a game object.
	 * @param gameName
	 * @param publisher
	 * @param releaseYear
	 */
	public Games(String gameName, Publishers publisher, int releaseYear) {
		this.gameName = gameName;
		this.publisher = publisher;
		this.releaseYear = releaseYear;
	}
	
	/**
	 * Get the game id of the Games Object.
	 * @return the game Id.
	 */
	public int getGameId() {
		return this.gameId;
	}
	
	/**
	 * Change the game id of the Games Object.
	 * @param gameId
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	/**
	 * Get the game name of the Games Object.
	 * @return the game name.
	 */
	public String getGameName() {
		return this.gameName;
	}
	
	/**
	 * Update the game name of the Games Object.
	 * @param gameName
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * Get the Publishers Object assigned to the Games Object.
	 * @return get the Publishers Object
	 */
	public Publishers getPublisher() {
		return this.publisher;
	}
	
	/**
	 * Change the Publishers Object assigned to the Games Object.
	 * @param publisher
	 */
	public void setPublisher(Publishers publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * Get the release year of the games object.
	 * @return the year.
	 */
	public int getReleaseYear() {
		return this.releaseYear;
	}
	
	/**
	 * Change the release year of the games object.
	 * @param releaseYear
	 */
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public String toString() {
		return String.format("%d: %s (%s) %d", gameId, gameName, publisher.toString(), releaseYear);
	}
}
