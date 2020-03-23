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

import gameranker.dal.UsersDao;
import gameranker.model.Users;

@WebServlet("/userdelete")
public class UserDelete extends HttpServlet{
	
protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete User");        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userId = req.getParameter("userId");
        int userIdInt = 0;
        try {
        	userIdInt = Integer.parseInt(userId);
	        try {
	        	Users user = UsersDao.getInstance().getUserByUserId(userIdInt);
	        	String userString = user.toString();
	        	user = usersDao.delete(user);
	        	// Update the message.
		        if (user == null) {
		            messages.put("success", "Successfully deleted " + userString);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("success", "Failed to delete " + userId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        } catch (NumberFormatException e) {
            messages.put("success", "Invalid UserId");
            messages.put("disableSubmit", "true");
        }
        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }

}
