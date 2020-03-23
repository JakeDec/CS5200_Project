package gameranker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameranker.dal.GamesDao;
import gameranker.dal.ReviewsDao;
import gameranker.dal.UserReviewsDao;
import gameranker.dal.UsersDao;
import gameranker.model.Games;
import gameranker.model.Reviews;
import gameranker.model.UserReviews;
import gameranker.model.Users;

@WebServlet("/userreviewscreate")
public class UserReviewsCreate extends HttpServlet{
	
protected UserReviewsDao userReviewsDao;
protected ReviewsDao reviewsDao;
protected UsersDao userDao;
protected GamesDao gamesDao;
	
	@Override
	public void init() throws ServletException {
		userReviewsDao = UserReviewsDao.getInstance();
		reviewsDao = ReviewsDao.getInstance();
		userDao = UsersDao.getInstance();
		gamesDao = GamesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserReviewsCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int userId = Integer.valueOf(req.getParameter("userId"));
        if (userId == 0) {
            messages.put("success", "Invalid UserId");
        } else {
        	// Create the UserReview.
	        try {
	        	int gameId = Integer.valueOf(req.getParameter("gameId"));
	        	float score = Float.valueOf(req.getParameter("score"));
	        	String review = req.getParameter("review");
	        	
	        	System.out.println(gameId);
	        	
	        	Games game = gamesDao.getGameById(gameId); // Collect the Game
	        	Users user = userDao.getUserByUserId(userId); // Collect the User
	        	Reviews rev = new Reviews(game,review); //initialize a review
	        	
	        	rev = reviewsDao.create(rev); //store the review in the db.
	        	
	        	UserReviews userReview = new UserReviews(rev,user,score); //create the user review object.
	        	
	        	userReview = userReviewsDao.create(userReview);
	        	
	        	messages.put("success", "Successfully created User Review");
	        } catch (SQLException e) {
	        	messages.put("success", "Failed to find create review.");
	        }
        }
        
        req.getRequestDispatcher("/UserReviewsCreate.jsp").forward(req, resp);
    }


}
