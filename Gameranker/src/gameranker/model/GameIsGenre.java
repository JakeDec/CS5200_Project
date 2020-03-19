package gameranker.model;

public class GameIsGenre {
	protected Games game;
	protected Genres genre;
	
//	CREATE TABLE GameIsGenre (
//			  GameIdFk INT NOT NULL,
//			  GenreIdFk INT NOT NULL,
//			  CONSTRAINT GameIsGenreGamesFk
//			    FOREIGN KEY (GameIdFk)
//			    REFERENCES Games (GameId)
//			    ON UPDATE CASCADE ON DELETE CASCADE,
//			  CONSTRAINT GameIsGenreGenresFk
//			    FOREIGN KEY (GenreIdFk)
//			    REFERENCES Genres (GenreId)
//			    ON UPDATE CASCADE ON DELETE CASCADE
//			) ENGINE = InnoDB;
	
	/**
	 * Construct a GameIsGenre Object.
	 * @param game
	 * @param genre
	 */
	public GameIsGenre(Games game, Genres genre) {
		this.game = game;
		this.genre = genre;
	}
	
	/**
	 * Get the Game Object from the GameIsGenre Object.
	 * @return the game
	 */
	public Games getGame() {
		return this.game;
	}
	
	/**
	 * Change the Game Object from the GameIsGenre Object.
	 * @param game
	 */
	public void setGame(Games game) {
		this.game = game;
	}
	
	/**
	 * Get the genre from the GameIsGenre Object.
	 * @return the genre
	 */
	public Genres getGenre() {
		return this.genre;
	}
	
	/**
	 * Change the genre assigned to the GameIsGenre Object.
	 * @param genre
	 */
	public void setGenre(Genres genre) {
		this.genre = genre;
	}
	
}
