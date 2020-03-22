package gameranker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameranker.dal.UserReviewsDao;
import gameranker.dal.UsersDao;
import gameranker.model.UserReviews;
import gameranker.model.Users;

@WebServlet("/userreviewsread")
public class UserReviewsRead extends HttpServlet {
	
protected UserReviewsDao userReviewDao;
protected UsersDao userDao;
	
	@Override
	public void init() throws ServletException {
		userReviewDao = UserReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate name.
        // user review is retrieved from the URL query string.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid User Id.");
        } else {
        	Users user;
        	List<UserReviews> reviews;
        	try {
        		// Retrieve user
            	user = userDao.getUserByUserId(Integer.valueOf(userId));
        		//Retrieve all reviews for that user.
            	reviews = userReviewDao.getReviewsByUser(user);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + user.getUserName());
        }
        req.setAttribute("userid", userId);
        
        req.getRequestDispatcher("/UserReviewsRead.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate name.
     // user review is retrieved from the URL query string.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid User Id.");
        } else {
        	Users user;
        	List<UserReviews> reviews;
        	try {
        		// Retrieve user
            	user = userDao.getUserByUserId(Integer.valueOf(userId));
        		//Retrieve all reviews for that user.
            	reviews = userReviewDao.getReviewsByUser(user);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + user.getUserName());
        }
        req.setAttribute("userId", userId);
        
        req.getRequestDispatcher("/UserReviewsRead.jsp").forward(req, resp);
    }

}
