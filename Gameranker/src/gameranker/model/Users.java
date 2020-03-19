package gameranker.model;

public class Users {
	protected int userId;
	protected int steamId;
	protected String userName;
	
//	CREATE TABLE Users (
//			  UserId INT NOT NULL UNIQUE AUTO_INCREMENT,
//			  SteamId INT UNIQUE,
//			  UserName TEXT DEFAULT NULL,
//			  CONSTRAINT UserPk
//			    PRIMARY KEY (UserId)
//			) ENGINE = InnoDB;
//	
	
	
	/**
	 * Construct a User object with a steam id.
	 * @param userId
	 * @param steamId
	 * @param userName
	 */
	public Users(int userId, int steamId, String userName) {
		this.userId = userId;
		this.steamId = steamId;
		this.userName = userName;
	}
	
	/**
	 * Construct a User object without a steam id. 
	 * this.steamId = NULL.
	 * @param userId
	 * @param userName
	 */
	public Users(int userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	/**
	 * Get the User Id from the User object.
	 * @return the user id.
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Change the User ID.
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Get the Steam Id from the User object.
	 * @return the steam id.
	 */
	public int getSteamId() {
		return steamId;
	}
	
	/**
	 * Change the Steam ID.
	 * @param steamId
	 */
	public void setSteamId(int steamId) {
		this.steamId = steamId;
	}
	
	/**
	 * Get the Username from the User object.
	 * @return the username.
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Change the username.
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
