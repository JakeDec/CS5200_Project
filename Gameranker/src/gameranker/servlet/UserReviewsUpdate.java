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

import gameranker.dal.UserReviewsDao;
import gameranker.model.UserReviews;


//update score
//get review by id
@WebServlet("/userreviewsupdate")
public class UserReviewsUpdate extends HttpServlet {
	
protected UserReviewsDao userReviewsDao;
	
	@Override
	public void init() throws ServletException {
		userReviewsDao = UserReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve game and validate.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null) {
            messages.put("success", "Please enter a valid ReviewId.");
        } else {
        	try {
        		UserReviews rev = userReviewsDao.getUserReviewById(Integer.valueOf(reviewId));
        		if(rev == null) {
        			messages.put("success", "Review does not exist.");
        		}
        		req.setAttribute("reviewid", rev);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserReviewsUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve a game and validate.
     // Retrieve game and validate.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null) {
            messages.put("success", "Please enter a valid ReviewId.");
        } else {
        	try {
        		UserReviews rev = userReviewsDao.getUserReviewById(Integer.valueOf(reviewId));
        		if(rev == null) {
        			messages.put("success", "Review does not exist. No update.");
        		} else {
        			float score = Float.valueOf(req.getParameter("newscore"));
        			if (score < 0) {
        	            messages.put("success", "Please enter a valid score.");
        	        } else {
        	        	rev = userReviewsDao.updateScore(rev, score);
        	        	messages.put("success", "Successfully updated review");
        	        }
        		}
        		req.setAttribute("reviewid", rev);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserReviewsUpdate.jsp").forward(req, resp);
    }

}
