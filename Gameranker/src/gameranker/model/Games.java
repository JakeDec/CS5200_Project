package gameranker.model;

import java.util.List;

public class Games {
	protected int gameId;
	protected String gameName;
	protected Publishers publisher;
	protected int releaseYear;
	protected List<Platforms> platforms;
	protected List<Genres> genres;
	
//	CREATE TABLE Games (
//			  GameId INT NOT NULL UNIQUE AUTO_INCREMENT,
//			  GameName VARCHAR(255) NOT NULL UNIQUE,
//			  PublisherIdFk INT,
//			  ReleaseYear INT,
//			  CONSTRAINT GamesPk
//			    PRIMARY KEY (GameId),
//			  CONSTRAINT GamesPublishersFk
//			    FOREIGN KEY (PublisherIdFk)
//			    REFERENCES Publishers (PublisherId)
//			    ON UPDATE CASCADE ON DELETE SET NULL
//			) ENGINE = InnoDB;
	
	/**
	 * Private method for building a game object.
	 * @param gameId
	 * @param gameName
	 * @param publisher
	 * @param releaseYear
	 */
	private void setUpGames(int gameId, String gameName, Publishers publisher, int releaseYear) {
		this.gameId = gameId;
		this.gameName = gameName;
		this.publisher = publisher;
		this.releaseYear = releaseYear;
	}
	
	/**
	 * Construct a game object when the platforms and genres are not known.
	 * To assign a list of platforms or genres later, use the respective set functions.
	 * @param gameId
	 * @param gameName
	 * @param publisher
	 * @param releaseYear
	 */
	public Games(int gameId, String gameName, Publishers publisher, int releaseYear) {
		setUpGames(gameId,gameName,publisher,releaseYear); //call private method.
	}
	
	/**
	 * Construct a game object when the platforms and genres are known.
	 * @param gameId
	 * @param gameName
	 * @param publisher
	 * @param releaseYear
	 * @param platforms
	 * @param genres
	 */
	public Games(int gameId, String gameName, Publishers publisher, int releaseYear, List<Platforms> platforms, List<Genres> genres) {
		setUpGames(gameId,gameName,publisher,releaseYear); //call private method.
		this.platforms = platforms;
		this.genres = genres;
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
	
	/**
	 * Collect a list of all platforms that are supported for this Game Object.
	 * @return list of platforms.
	 */
	public List<Platforms> getPlatforms() {
		return this.platforms;
	}
	
	/**
	 * Change the platforms list assigned to the Game Object.
	 * @param platforms
	 */
	public void setPlatforms(List<Platforms> platforms) {
		this.platforms = platforms;
	}
	
	/**
	 * Collect a list of all of the genres that are supported for this Game Object.
	 * @return list of genres.
	 */
	public List<Genres> getGenres() {
		return this.genres;
	}
	
	/**
	 * Change the genres list assigned to the Game Object.
	 * @param genres
	 */
	public void setGenres(List<Genres> genres) {
		this.genres = genres;
	}

}
