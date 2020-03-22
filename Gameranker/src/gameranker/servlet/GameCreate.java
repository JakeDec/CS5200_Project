package gameranker.servlet;
import gameranker.dal.GamesDao;
import gameranker.model.Games;
import gameranker.model.Publishers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gamecreate")
public class GameCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/GameCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String gameName = req.getParameter("gameName");
        if (gameName == null || gameName.trim().isEmpty()) {
            messages.put("success", "Invalid Game");
        } else {
        	// Create the Game.
        	String publisher = req.getParameter("publisher");
        	int year = Integer.valueOf(req.getParameter("releaseYear"));
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Publishers pub = new Publishers(publisher);
	        	Games game = new Games(gameName,pub,year);
	        	game = gamesDao.create(game);
	        	messages.put("success", "Successfully created " + gameName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/GameCreate.jsp").forward(req, resp);
    }
}
