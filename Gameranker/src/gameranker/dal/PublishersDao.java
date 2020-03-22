package gameranker.dal;

import gameranker.model.Publishers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PublishersDao {
	protected ConnectionManager connectionManager;

	private static PublishersDao instance = null;
	protected PublishersDao() {
		connectionManager = new ConnectionManager();
	}
	public static PublishersDao getInstance() {
		if(instance == null) {
			instance = new PublishersDao();
		}
		return instance;
	}

	public Publishers create(Publishers publisher) throws SQLException {
		String insert = "INSERT INTO Publishers (publisherName) " + 
				"VALUES (?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, publisher.getPublisherName());
			
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int resultId = -1;

			if (resultKey.next()) {
				resultId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			publisher.setPublisherId(resultId);

			return publisher;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Publishers getPublisherById(int publisherId) throws SQLException {
		String select = "SELECT * FROM Publishers WHERE PublisherId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, publisherId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String publisherName = results.getString("PublisherName");

				return new Publishers(publisherId, publisherName);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (selectStmt != null) {
				selectStmt.close();
			}

			if (results != null) {
				results.close();
			}
		}

		return null;
	}

	public Publishers getPublisherByName(String publisherName) throws SQLException {
		String select = "SELECT * FROM Publishers WHERE PublisherName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, publisherName);
			results = selectStmt.executeQuery();

			if (results.next()) {
				int publisherId = results.getInt("PublisherId");

				return new Publishers(publisherId, publisherName);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (selectStmt != null) {
				selectStmt.close();
			}

			if (results != null) {
				results.close();
			}
		}

		return null;
	}

	public Publishers updatePublisherName(Publishers publisher, String newPublisherName) throws SQLException {
		String update = "UPDATE Publishers SET PublisherName = ? WHERE PublisherId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setString(1, newPublisherName);
			updateStmt.setInt(2, publisher.getPublisherId());
			updateStmt.executeUpdate();

			publisher.setPublisherName(newPublisherName);

			return publisher;
		} catch (SQLException e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Publishers delete(Publishers publisher) throws SQLException {
		String delete = "DELETE FROM Publishers WHERE PublisherId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			
			deleteStmt.setInt(1, publisher.getPublisherId());
			
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
