package gameranker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameranker.dal.PublishersDao;
import gameranker.model.Publishers;


@WebServlet("/publisherdelete")
public class PublisherDelete extends HttpServlet {
	
	protected PublishersDao publishersDao;
	
	@Override
	public void init() throws ServletException {
		publishersDao = PublishersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Publisher");        
        req.getRequestDispatcher("/PublisherDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String publisherName = req.getParameter("publishername");
		
        if (publisherName == null || publisherName.trim().isEmpty()) {
            messages.put("success", "Invalid publisher name.");
        } else {
	        try {
	        	Publishers publisher = publishersDao.getPublisherByName(publisherName);
	        	
		        if (publisher == null) {
		        	messages.put("success", "Failed to delete publisher, no publisher with name found: " + publisherName);
		        }
		        else {
		        	publisher = publishersDao.delete(publisher);

		        	if (publisher == null) {
		        		messages.put("success", "Successfully deleted publisher with name: " + publisherName);
		        	} else {
		        		messages.put("success", "Failed to delete publisher with name: " + publisherName);
		        	}
		        }
	        } catch (SQLException e) {
        		messages.put("success", "Failed to delete publisher with name: " + publisherName);
	        }
        }
        
        req.getRequestDispatcher("/PublisherDelete.jsp").forward(req, resp);
    }
}
