package gameranker.dal;
import java.sql.Connection;
import gameranker.model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {
	protected ConnectionManager connectionManager;

	private static QueryDao instance = null;
	protected QueryDao() {
		connectionManager = new ConnectionManager();
	}
	public static QueryDao getInstance() {
		if(instance == null) {
			instance = new QueryDao();
		}
		return instance;
	}

	public List<QueryResult> query1() throws SQLException {
		String select = "SELECT \r\n" + 
				"    games.GameName,\r\n" + 
				"    AVG(filtered.PlayTime) AS AveragePlayTime,\r\n" + 
				"    COUNT(filtered.PlayTime) AS Users,\r\n" + 
				"    COUNT(filtered.PlayTime) / MAX(filtered.UserIdFk) * 100 AS PercentageOwnerShip\r\n" + 
				"FROM\r\n" + 
				"    (SELECT * FROM userhasgame\r\n" + 
				"    WHERE PlayTime > 0) AS filtered\r\n" + 
				"        INNER JOIN\r\n" + 
				"    games ON filtered.GameIdFk = games.GameId\r\n" + 
				"GROUP BY GameIdFk\r\n" + 
				"HAVING COUNT(filtered.playtime) > MAX(filtered.UserIdFk) *.01\r\n" + 
				"ORDER BY AveragePlayTime DESC\r\n" + 
				"LIMIT 20;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("GameName"));
				temp.setResult2(rs.getString("AveragePlayTime"));
				temp.setResult3(rs.getString("Users"));
				temp.setResult4(rs.getString("PercentageOwnerShip"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	
	public List<QueryResult> query2() throws SQLException {
		String select = "SELECT \r\n" + 
				"    games.GameName,\r\n" + 
				"    AVG(filtered.PlayTime) AS AveragePlayTime,\r\n" + 
				"    COUNT(filtered.PlayTime) AS Users\r\n" + 
				"FROM\r\n" + 
				"    (SELECT \r\n" + 
				"        *\r\n" + 
				"    FROM\r\n" + 
				"        userhasgame\r\n" + 
				"    WHERE\r\n" + 
				"        PlayTime <= 2) AS filtered\r\n" + 
				"        INNER JOIN\r\n" + 
				"    games ON filtered.GameIdFk = games.GameId\r\n" + 
				"GROUP BY GameIdFk\r\n" + 
				"ORDER BY Users DESC\r\n" + 
				"LIMIT 20;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("GameName"));
				temp.setResult2(rs.getString("AveragePlayTime"));
				temp.setResult3(rs.getString("Users"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	public List<QueryResult> query3() throws SQLException {
		String select = "SELECT \r\n" + 
				"    genres.Genre, COUNT(genres.Genre) AS NumberGames\r\n" + 
				"FROM\r\n" + 
				"    gameisgenre\r\n" + 
				"        INNER JOIN\r\n" + 
				"    genres ON genres.GenreId = gameisgenre.GenreIdFk\r\n" + 
				"GROUP BY genres.Genre\r\n" + 
				"ORDER BY NumberGames DESC\r\n" + 
				"LIMIT 20;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("Genre"));
				temp.setResult2(rs.getString("NumberGames"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	public List<QueryResult> query4() throws SQLException {
		String select = "SELECT \r\n" + 
				"    GameName, AVG(criticreviews.Score) AS AverageScore\r\n" + 
				"FROM\r\n" + 
				"    reviews\r\n" + 
				"        INNER JOIN\r\n" + 
				"    criticreviews ON reviews.ReviewId = criticreviews.ReviewIdFk\r\n" + 
				"        INNER JOIN\r\n" + 
				"    games ON games.GameId = reviews.GameIdFk\r\n" + 
				"GROUP BY GameName\r\n" + 
				"ORDER BY AverageScore DESC\r\n" + 
				"LIMIT 20;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("GameName"));
				temp.setResult2(rs.getString("AverageScore"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	public List<QueryResult> query5(String genre) throws SQLException {
		String select = "SELECT\r\n" + 
				"	GameName, genres.Genre,\r\n" + 
				"	(AVG(IFNULL\r\n" + 
				"(criticreviews.Score, IFNULL(userreviews.Score * 10, 0))) \r\n" + 
				"+\r\n" + 
				"    	(AVG(IFNULL\r\n" + 
				"(userreviews.Score * 10, IFNULL(criticreviews.Score, 0)))))\r\n" + 
				"	/ 2 AS ComboScore\r\n" + 
				"FROM\r\n" + 
				"	reviews\r\n" + 
				"    	LEFT JOIN criticreviews \r\n" + 
				"ON reviews.ReviewId = criticreviews.ReviewIdFk\r\n" + 
				"    	LEFT JOIN games \r\n" + 
				"ON games.GameId = reviews.GameIdFk\r\n" + 
				"    	LEFT JOIN userreviews \r\n" + 
				"ON reviews.ReviewId = userreviews.ReviewIdFk\r\n" + 
				"    	LEFT JOIN gameisgenre \r\n" + 
				"ON games.GameId = gameisgenre.GameIdFk\r\n" + 
				"    	LEFT JOIN genres \r\n" + 
				"ON genres.GenreId = gameisgenre.GenreIdFk\r\n" + 
				"GROUP BY GameName\r\n" + 
				"HAVING genres.Genre = ?\r\n" + 
				"ORDER BY ComboScore DESC\r\n" + 
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			stmt.setString(1, genre);

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("GameName"));
				temp.setResult2(rs.getString("Genre"));
				temp.setResult3(rs.getString("ComboScore"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	public List<QueryResult> query6() throws SQLException {
		String select = "SELECT\r\n" + 
				"	GameName, COUNT(gameonplatform.PlatformIdFk) as PlatformCount\r\n" + 
				"FROM\r\n" + 
				"	games\r\n" + 
				"    	    INNER JOIN\r\n" + 
				"	gameonplatform ON gameonplatform.GameIdFk = games.GameId\r\n" + 
				"GROUP BY GameName\r\n" + 
				"ORDER BY PlatformCount DESC\r\n" + 
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("GameName"));
				temp.setResult2(rs.getString("PlatformCount"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	public List<QueryResult> query8() throws SQLException {
		String select = "SELECT Genre, Avg(Score) AverageScore from games\r\n" + 
				"LEFT JOIN GameIsGenre on GameIsGenre.GameIdFK = games.GameId\r\n" + 
				"LEFT JOIN Genres on Genres.GenreId = GameIsGenre.GenreIdFk\r\n" + 
				"LEFT JOIN Reviews on games.GameId = Reviews.GameIdFk\r\n" + 
				"LEFT JOIN CriticReviews on Reviews.ReviewId = CriticReviews.ReviewIdFk\r\n" + 
				"GROUP BY Genre\r\n" + 
				"ORDER BY 2 Desc\r\n" + 
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("Genre"));
				temp.setResult2(rs.getString("AverageScore"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	public List<QueryResult> query9() throws SQLException {
		String select = "SELECT Genre, Avg(Playtime)as AveragePlaytime from games\r\n" + 
				"LEFT JOIN GameIsGenre on GameIsGenre.GameIdFK = games.GameId\r\n" + 
				"LEFT JOIN Genres on Genres.GenreId = GameIsGenre.GenreIdFk\r\n" + 
				"INNER JOIN UserHasGame on Games.GameId = UserHasGame.GameIdFk\r\n" + 
				"GROUP BY Genre\r\n" + 
				"ORDER BY 2 DESC\r\n" + 
				"LIMIT 20";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("Genre"));
				temp.setResult2(rs.getString("AveragePlaytime"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
	public List<QueryResult> query10() throws SQLException {
		String select = "SELECT GameName, Avg(Score) as AverageUserRating from games\r\n" + 
				"INNER JOIN UserHasGame on games.GameId = UserHasGame.GameIdFk\r\n" + 
				"INNER JOIN Users on UserHasGame.UserIdFK = Users.UserId\r\n" + 
				"INNER JOIN Reviews on games.GameId = Reviews.GameIdFk\r\n" + 
				"INNER JOIN UserReviews on Users.UserID = UserReviews.UserIdFk\r\n" + 
				"WHERE SteamID IS NOT NULL\r\n" + 
				"GROUP BY GameName\r\n" + 
				"ORDER BY 2 DESC\r\n" + 
				"LIMIT 20;";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<QueryResult> results = new ArrayList<QueryResult>();
		try {
			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(select);

			//stmt.setString(1, user.getUserName());

			rs = stmt.executeQuery();

			while(rs.next()) {
				QueryResult temp = new QueryResult();
				temp.setResult1(rs.getString("GameName"));
				temp.setResult2(rs.getString("AveragePlayTime"));
				temp.setResult3(rs.getString("Users"));
				temp.setResult4(rs.getString("PercentageOwnerShip"));
				results.add(temp);
			}	
			stmt.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}