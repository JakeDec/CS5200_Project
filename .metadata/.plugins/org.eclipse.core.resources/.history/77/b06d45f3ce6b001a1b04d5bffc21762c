package gameranker.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gameranker.model.Platforms;

public class PlatformsDao {
	protected ConnectionManager connectionManager;

	private static PlatformsDao instance = null;
	protected PlatformsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlatformsDao getInstance() {
		if(instance == null) {
			instance = new PlatformsDao();
		}
		return instance;
	}

	public Platforms create(Platforms platform) throws SQLException {
		String insert = "INSERT INTO Platforms (platformName) " + 
				"VALUES (?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);

			insertStmt.setString(1, platform.getPlatformName());
			
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int resultId = -1;

			if (resultKey.next()) {
				resultId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			platform.setPlatformId(resultId);

			return platform;
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

	public Platforms getPlatformById(int platformId) throws SQLException {
		String selectReview = "SELECT * FROM Platforms WHERE PlatformId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, platformId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String platformName = results.getString("PlatformName");

				return new Platforms(platformId, platformName);
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

	public Platforms delete(Platforms platform) throws SQLException {
		String delete = "DELETE FROM Platforms WHERE PlatformId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			
			deleteStmt.setInt(1, platform.getPlatformId());
			
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
