package gameranker.model;

public class Genres {
	protected int genreId;
	protected String genre;

	//	CREATE TABLE Genres (
	//  	GenreId INT NOT NULL UNIQUE AUTO_INCREMENT,
	//  	Genre VARCHAR(255) UNIQUE,
	//  	CONSTRAINT GenresPk
	//  		PRIMARY KEY (GenreId)
	//	) ENGINE = InnoDB;

	/**
	 * Construct a Genres Object.
	 * @param genreId
	 * @param genre
	 */
	public Genres(int genreId, String genre) {
		this.genreId = genreId;
		this.genre = genre;
	}

	/**
	 * Construct a Genres Object.
	 * @param genre
	 */
	public Genres(String genre) {
		this.genre = genre;
	}

	/**
	 * Get the genre id of the Genre Object.
	 * @return the genre id.
	 */
	public int getGenreId() {
		return this.genreId;
	}

	/**
	 * Change the genre id of the Genre Object.
	 * @param genreId
	 */
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	/**
	 * Get the genre of the Genre Object.
	 * @return the genre
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * Update the genre of the Genre Object.
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

}
