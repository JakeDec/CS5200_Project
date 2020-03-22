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

		System.out.println("Changing the userName to Jake Dec");
		u1 = usersDao.setUserName(u1, "Jake Dec");
		System.out.println(u1);
		
		System.out.println("Adding a steamId to Jake Dec");
		u1 = usersDao.setSteamId(u1, 8675309);
		System.out.println(u1);

		System.out.println("Checking to see if getting user by id works");
		Users u2 = usersDao.getUserById(1);
		System.out.println(u2);
		
		System.out.println("\n");
		
		// PUBLISHER DAO
		
		PublishersDao publishersDao = PublishersDao.getInstance();

		System.out.println("Creating Publisher GoldTeam");
		Publishers p1 = new Publishers("GoldTeam");
		p1 = publishersDao.create(p1);
		System.out.println(p1);
		
		System.out.println("Updating publish name to Gold Team");
		p1 = publishersDao.updatePublisherName(p1, "Gold Team");
		System.out.println(p1);
		
		System.out.println("Getting Publisher 1 by ID");
		Publishers p2 = publishersDao.getPublisherById(1);
		System.out.println(p2);
		
		System.out.println("\n");
		
		
		// GAMES DAO	
		GamesDao gamesDao = GamesDao.getInstance();
		System.out.println("Creating new game.");
		Games g1 = new Games("Gold Team Rules", p2, 2019);
		g1 = gamesDao.create(g1);
		System.out.println(g1);
		
		System.out.println("Updating publisher to Gold Team");
		g1 = gamesDao.setPublisher(g1, p1);
		System.out.println(g1);
		
		System.out.println("Updating publish year to 2020");
		g1 = gamesDao.setReleaseYear(g1, 2020);
		System.out.println(g1);

		System.out.println("Getting Game 2 by ID");
		Games g2 = gamesDao.getGameById(2);
		System.out.println(g2);
		
		
		
		gamesDao.delete(g1);
		publishersDao.delete(p1);
		usersDao.delete(u1);
		
		
		
		
		
		
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
