package gameranker.servlet;

import gameranker.dal.GamesDao;
import gameranker.model.Games;
import gameranker.model.Publishers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gameupdate")
public class GameUpdate extends HttpServlet {
	
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

        // Retrieve game and validate.
        String gameId = req.getParameter("gameid");
        if (gameId == null) {
            messages.put("success", "Please enter a valid GameId.");
        } else {
        	try {
        		Games game = gamesDao.getGameById(Integer.valueOf(gameId));
        		if(game == null) {
        			messages.put("success", "Game does not exist.");
        		}
        		req.setAttribute("gameid", game);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/GameUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve a game and validate.
        int gameId = Integer.valueOf(req.getParameter("gameid"));
        if (gameId == 0) {
            messages.put("success", "Please enter a valid GameId.");
        } else {
        	try {
        		Games game = gamesDao.getGameById(gameId); 
        		if(game == null) {
        			messages.put("success", "Game does not exist. No update to perform.");
        		} else {
        			String newPublisher = req.getParameter("newpublisher");
        			if (newPublisher == null || newPublisher.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Publisher.");
        	        } else {
        	        	Publishers pub = new Publishers(newPublisher);
        	        	game = gamesDao.setPublisher(game, pub);
        	        	messages.put("success", "Successfully updated " + game.getGameName());
        	        }
        		}
        		req.setAttribute("gameid", game);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/GameUpdate.jsp").forward(req, resp);
    }
}
