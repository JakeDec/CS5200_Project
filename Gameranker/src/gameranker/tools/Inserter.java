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
		
		// USER DAO
		UsersDao usersDao = UsersDao.getInstance();
		Users u1 = new Users("Jake");
		System.out.println("Adding user Jake");
		u1 = usersDao.create(u1);
		
		System.out.println("Checking to see if id was added");
		System.out.println(u1);
		
		System.out.println("Changing the userName");
		u1 = usersDao.setUserName(u1, "Jake Dec");
		System.out.println(u1);

		System.out.println("Checking to see if getting user by id works");
		Users u2 = usersDao.getUserById(1);
		System.out.println(u2);
		
		
		
		
		
		usersDao.delete(u1);
		
		
//		PublishersDao publishersDao = PublishersDao.getInstance();
//		Publishers publisher = new Publishers("Gold Team");
//		publishersDao.create(publisher);
//		
//		GamesDao gamesDao = GamesDao.getInstance();
//		Games game = new Games("Gold Team Rules", publisher, 2020);
//		gamesDao.create(game);
//		
//		GenresDao genresDao = GenresDao.getInstance();
//		Genres genre = new Genres("Project");
//		genresDao.create(genre);
//		
//		GameIsGenreDao gameIsGenreDao = GameIsGenreDao.getInstance();
//		GameIsGenre gameIsGenre = new GameIsGenre(game, genre);
//		gameIsGenreDao.create(gameIsGenre);
//		
//		PlatformsDao platformsDao = PlatformsDao.getInstance();
//		Platforms platform = new Platforms("Classroom");
//		platformsDao.create(platform);
//		
//		GameOnPlatformDao gameOnPlatformDao = GameOnPlatformDao.getInstance();
//		GameOnPlatform gameOnPlatform = new GameOnPlatform(game, platform);
//		gameOnPlatformDao.create(gameOnPlatform);
//		
//		//
//		gameOnPlatformDao.delete(gameOnPlatform);
//		platformsDao.delete(platform);
//		gameIsGenreDao.delete(gameIsGenre);
//		genresDao.delete(genre);
//		gamesDao.delete(game);
//		publishersDao.delete(publisher);
		
		
//		
//		
//		
//		// DAO instances.
//		UserHasGameDao userHasGameDao = UserHasGameDao.getInstance();
//		UsersDao usersDao = UsersDao.getInstance();
//		
//		ReviewsDao reviewsDao = ReviewsDao.getInstance();
//		CriticReviewsDao criticReviewsDao = CriticReviewsDao.getInstance();
//		UserReviewsDao userReviewsDao = UserReviewsDao.getInstance();
		
	}
}