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

@WebServlet("/usercreate")
public class UserCreate extends HttpServlet  {

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
		//Just render the JSP.   
		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String userName = req.getParameter("userName");
		String steamId = req.getParameter("steamId");
		int steamIdInt = 0;
		try {
			if (steamId != null && !steamId.trim().isEmpty()) {
				steamIdInt = Integer.parseInt(steamId);
			}
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid Username");
		} else {
			// Create the User.
			try {
				Users user = new Users(-1, steamIdInt, userName);
				user = usersDao.create(user);
				messages.put("success", "Successfully created " + user);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		} catch (NumberFormatException e) {
            messages.put("success", "Invalid UserId");
            messages.put("disableSubmit", "true");		
		}

		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}

}
