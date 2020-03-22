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

@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
	
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

     // Retrieve a user and validate.
        int userId = Integer.valueOf(req.getParameter("userid"));
        if (userId == 0) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	try {
        		Users user = usersDao.getUserByUserId(userId);
        		if(user == null) {
        			messages.put("success", "User does not exist.");
        		}
        		req.setAttribute("userid", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve a user and validate.
        int userId = Integer.valueOf(req.getParameter("userid"));
        if (userId == 0) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	try {
        		Users user = usersDao.getUserByUserId(userId) ;
        		if(user == null) {
        			messages.put("success", "User does not exist. No update to perform.");
        		} else {
        			String newUserName = req.getParameter("newusername");
        			if (newUserName == null || newUserName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid UserName.");
        	        } else {
        	        	user = usersDao.setUserName(user, newUserName);
        	        	messages.put("success", "Successfully updated " + user.getUserName());
        	        }
        		}
        		req.setAttribute("userid", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }

}
