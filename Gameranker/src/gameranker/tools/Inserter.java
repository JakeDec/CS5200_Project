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
		Users u2 = usersDao.getUserByUserId(1);
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

		System.out.println("Getting Game Path of Exile by Name");
		Games g3 = gamesDao.getGameByName("Path of Exile");
		System.out.println(g3);
		System.out.println("\n");
		
		// REVIEWS DAOS

		Reviews r1 = new Reviews(g1, "Great Game!7");
		Reviews r2 = new Reviews(g1, "Awesome Game!8");
		UserReviews ur1 = new UserReviews(r1, u1, 10.0f);
		CriticReviews cr1 = new CriticReviews(r2, "Critic", 10.0f);
		UserReviewsDao userReviewsDao = UserReviewsDao.getInstance();
		CriticReviewsDao criticReviewsDao = CriticReviewsDao.getInstance();

		System.out.println("Creating a User Review");
		ur1 = userReviewsDao.create(ur1);
		System.out.println(ur1);

		System.out.println("Getting a User Review 5 by ID");
		UserReviews ur2 = userReviewsDao.getUserReviewById(5);
		System.out.println(ur2);
		
		System.out.println("Getting all user reviews by Jake Dec");
		List<UserReviews> ur3 = userReviewsDao.getReviewsByUser(u1);
		System.out.println(ur3);

		System.out.println("\n");
		
		System.out.println("Creating a Critic Review");
		cr1 = criticReviewsDao.create(cr1);
		System.out.println(cr1);
		
		System.out.println("Getting a Critic Review 293670 by ID");
		CriticReviews cr2 = criticReviewsDao.getCriticReviewById(293670);
		System.out.println(cr2);
		
		System.out.println("Getting all critc reviews by Critic");
		List<CriticReviews> cr3 = criticReviewsDao.getReviewsByCriticName("Critic");
		System.out.println(cr3);
		
		// GENRE DAO
		GenresDao genresDao = GenresDao.getInstance();
		System.out.println("Creating a genre");
		Genres genre1 = new Genres("SQL Project");
		genre1 = genresDao.create(genre1);
		System.out.println(genre1);
		
		System.out.println("Getting a Genre 1 by ID");
		Genres genre2 = genresDao.getGenreById(1);
		System.out.println(genre2);

		System.out.println("\n");

		// GAME IS GENRE DAO
		GameIsGenreDao gameIsGenreDao = GameIsGenreDao.getInstance();
		GameIsGenre gig1 = new GameIsGenre(g1, genre1);
		System.out.println("Adding Genre to Game");
		gig1 = gameIsGenreDao.create(gig1);
		System.out.println(gig1);
		System.out.println("Getting genres for " + g1.toString());
		List<Genres> genres = gameIsGenreDao.getGenresForGame(g1);
		System.out.println(genres);

		System.out.println("\n");
		
		//PLATFORM - GAME ON PLATFORM
		
		PlatformsDao platformsDao = PlatformsDao.getInstance();
		System.out.println("Creating a platform: Classroom");
		Platforms plat1 = new Platforms("Classroom");
		plat1 = platformsDao.create(plat1);
		System.out.println(plat1);

		System.out.println("Looking up platform 42");
		Platforms plat2 = platformsDao.getPlatformById(42);
		System.out.println(plat2);
		System.out.println("\n");
		

		GameOnPlatformDao gameOnPlatformDao = GameOnPlatformDao.getInstance();
		GameOnPlatform gop1 = new GameOnPlatform(g1, plat1);
		System.out.println("Adding Platform to Game");
		gameOnPlatformDao.create(gop1);
		System.out.println(gop1);

		System.out.println("Getting platforms for " + g2.toString());
		List<Platforms> platforms = gameOnPlatformDao.getPlatformsForGame(g2);
		System.out.println(platforms);
		
		System.out.println("\n");
		
		// USERHASGAME DAO
		
		UserHasGameDao userHasGameDao = UserHasGameDao.getInstance();
		UserHasGame userHasGame = new UserHasGame(u1, g1, 99.99f);
		System.out.println("Creating a User Has Game entry");
		userHasGameDao.create(userHasGame);
		System.out.println(userHasGameDao);
		
		System.out.println("Getting games owned by the User Id 1:");
		
		List<UserHasGame> userHasGames = userHasGameDao.getUserHasGamesByUserId(u2.getUserId());
		for (UserHasGame uhg: userHasGames) {
			System.out.println(uhg);
		}
		


		// Remove our new Dummy Data
		userHasGameDao.delete(userHasGame);
		gameOnPlatformDao.delete(gop1);
		platformsDao.delete(plat1);
		gameIsGenreDao.delete(gig1);
		genresDao.delete(genre1);
		criticReviewsDao.delete(cr1);
		userReviewsDao.delete(ur1);
		gamesDao.delete(g1);
		publishersDao.delete(p1);
		usersDao.delete(u1);
		
	}
}
