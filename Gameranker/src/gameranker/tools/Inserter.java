package gameranker.tools;
import gameranker.dal.*;
import gameranker.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		GameIsGenreDao gameIsGenreDao = GameIsGenreDao.getInstance();
		GameOnPlatformDao gameOnPlatformDao = GameOnPlatformDao.getInstance();
		GamesDao gamesDao = GamesDao.getInstance();
		GenresDao genresDao = GenresDao.getInstance();
		PlatformsDao platformsDao = PlatformsDao.getInstance();
		PublishersDao publishersDao = PublishersDao.getInstance();
		UserHasGameDao userHasGameDao = UserHasGameDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		CriticReviewsDao criticReviewsDao = CriticReviewsDao.getInstance();
		UserReviewsDao userReviewsDao = UserReviewsDao.getInstance();
		
	}
}
