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
import gameranker.model.Games;

@WebServlet("/gameread")
public class GameRead extends HttpServlet{
	
protected GamesDao gamesDao;
	
	@Override
	public void init() throws ServletException {
		gamesDao = GamesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate name.
        // gamename is retrieved from the URL query string.
        String gameName = req.getParameter("gamename");
        if (gameName == null || gameName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid game name.");
        } else {
        	// Retrieve game, and store as a message.
        	Games game;
        	try {
            	game = gamesDao.getGameByName(gameName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + game.getGameName());
        }
        req.setAttribute("games", gameName);
        
        req.getRequestDispatcher("/GameRead.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate name.
        String gameName = req.getParameter("gamename");
        if (gameName == null || gameName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid game name.");
        } else {
        	// Retrieve game, and store as a message.
        	Games game;
        	try {
            	game = gamesDao.getGameByName(gameName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + game.getGameName());
        }
        req.setAttribute("games", gameName);
        
        req.getRequestDispatcher("/GameRead.jsp").forward(req, resp);
    }

}
