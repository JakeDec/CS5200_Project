package gameranker.model;

public class Publishers {
	protected int publisherId;
	protected String publisherName;

	//	CREATE TABLE Publishers (
	//		PublisherId INT NOT NULL UNIQUE AUTO_INCREMENT,
	//		PublisherName VARCHAR(255) NOT NULL UNIQUE,
	//		CONSTRAINT PublisherPk
	//			PRIMARY KEY (PublisherId)
	//	) ENGINE = InnoDB;
	
	/**
	 * Construct a Publishers Object.
	 * @param publisherId
	 * @param publisherName
	 */
	public Publishers(int publisherId, String publisherName) {
		this.publisherId = publisherId;
		this.publisherName = publisherName;
	}
	
	/**
	 * Construct a Publishers Object.
	 * @param publisherId
	 * @param publisherName
	 */
	public Publishers(String publisherName) {
		this.publisherName = publisherName;
	}
	
	/**
	 * Get the publisher id of the Publishers Object.
	 * @return the publisher id.
	 */
	public int getPublisherId() {
		return this.publisherId;
	}
	
	/**
	 * Change the publisher id of the Publishers Object.
	 * @param publisherId
	 */
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
	/**
	 * Get the publisher name assigned to the Publishers Object.
	 * @return the publisher name.
	 */
	public String getPublisherName() {
		return this.publisherName;
	}
	
	/**
	 * Update the publisher's name assigned to the Publishers Object.
	 * @param publisherName
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	public String toString() {
		return String.format("%d: %s", publisherId, publisherName);
	}
}
