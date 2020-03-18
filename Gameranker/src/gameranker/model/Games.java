package gameranker.model;

import java.util.List;

public class Games {
	private int gameId;
	private String gameName;
	private Publishers publisher;
	private int releaseYear;
	private List<Platforms> platforms;
	private List<Genres> genres;
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Publishers getPublisher() {
		return publisher;
	}
	public void setPublisher(Publishers publisher) {
		this.publisher = publisher;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public List<Platforms> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(List<Platforms> platforms) {
		this.platforms = platforms;
	}
	
	public List<Genres> getGenres() {
		return genres;
	}
	public void setGenres(List<Genres> genres) {
		this.genres = genres;
	}

}
