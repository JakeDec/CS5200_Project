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

@WebServlet("/userreviewsdelete")
public class UserReviewsDelete extends HttpServlet{
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete User Review");        
        req.getRequestDispatcher("/UserReviewsDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int id = Integer.valueOf(req.getParameter("Id"));
        if (id == 0) {
            messages.put("title", "Invalid Review");
            messages.put("disableSubmit", "true");
        } else {
	        try {
	        	// Delete the User Review.
		        UserReviews review = userReviewsDao.getUserReviewById(id);
	        	review = userReviewsDao.delete(review);
	        	// Update the message.
		        if (review == null) {
		            messages.put("title", "Successfully deleted the user review");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete the user review");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserReviewsDelete.jsp").forward(req, resp);
    }

}
