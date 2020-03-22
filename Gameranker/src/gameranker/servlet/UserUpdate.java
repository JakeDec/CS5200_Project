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
		int userId = 0;
		if (userId == 0) {
			messages.put("success", "Please enter a valid UserId.");
		} else {
			try {
				userId = Integer.valueOf(req.getParameter("userid"));
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
		int userIdInt = 0;
		int steamIdInt = 0;
		
		
		String userName = req.getParameter("newusername");
		String userId = req.getParameter("userid");
		String steamId = req.getParameter("newSteamId");

		try {
			if (!userId.trim().isEmpty()) {
				userIdInt = Integer.parseInt(userId);
			}
			if (!steamId.trim().isEmpty()) {
				steamIdInt = Integer.parseInt(steamId);
			}
		} catch (NumberFormatException e) {
			messages.put("success", "Please enter a valid ID.");
			req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
			return;
			//Pass
		}

		if (userIdInt == 0) {
			messages.put("success", "Please enter a valid UserId.");
			req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
			return;
		}

		try {
			Users user = usersDao.getUserByUserId(userIdInt);
			String userString = user.toString();
			if(user == null) {
				messages.put("success", "User does not exist. No update to perform.");
				req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
				return;
			} 
			if (!userName.trim().isEmpty()) {
				UsersDao.getInstance().setUserName(user, userName);
			}
			if (steamIdInt != 0) {
				UsersDao.getInstance().setSteamId(user, steamIdInt);
			}
			req.setAttribute("userid", user);
			messages.put("success", String.format("Updated %s \nto %s", userString, user.toString()));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}

}
