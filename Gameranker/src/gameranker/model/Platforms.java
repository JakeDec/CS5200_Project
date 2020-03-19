package gameranker.model;

public class Platforms {
	protected int platformId;
	protected String platformName;

	//	CREATE TABLE Platforms (
	//		PlatformId INT NOT NULL UNIQUE AUTO_INCREMENT,
	//		PlatformName VARCHAR(50) NOT NULL UNIQUE,
	//		CONSTRAINT PlatformPk
	//			PRIMARY KEY (PlatformId)
	//	) ENGINE = InnoDB;

	/**
	 * Construct a Platforms Object.
	 * @param platformId
	 * @param platformName
	 */
	public Platforms(int platformId, String platformName) {
		this.platformId = platformId;
		this.platformName = platformName;
	}
	
	/**
	 * Construct a Platforms Object.
	 * @param platformName
	 */
	public Platforms(String platformName) {
		this.platformName = platformName;
	}
	
	/**
	 * Get the platform id of the Platforms Object.
	 * @return the platform id.
	 */
	public int getPlatformId() {
		return this.platformId;
	}
	
	/**
	 * Change the platform id assigned to the Platforms Object.
	 * @param platformId
	 */
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	
	/**
	 * Get the platform name assigned to the Platforms Object.
	 * @return the platform name.
	 */
	public String getPlatformName() {
		return this.platformName;
	}
	
	/**
	 * Change the platform name assigned to the Platforms Object.
	 * @param platformName
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

}
