package gameranker.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameranker.dal.PublishersDao;
import gameranker.model.Publishers;

@WebServlet("/publisherread")
public class PublisherRead extends HttpServlet{
	
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
        
        // Retrieve and validate name.
        // publishername is retrieved from the URL query string.
        String publisherName = req.getParameter("publishername");
        if (publisherName == null || publisherName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid publisher name.");
        } else {
        	// Retrieve publisher, and store as a message.
        	Publishers publisher;
        	try {
            	publisher = publishersDao.getPublisherByName(publisherName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + publisher.getPublisherName());
        	
        	List<Publishers> publishers = new ArrayList<Publishers>();
        	publishers.add(publisher);
        	
            req.setAttribute("publishers", publishers);
        }
        
        req.getRequestDispatcher("/PublisherRead.jsp").forward(req, resp);
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
            messages.put("success", "Please enter a valid publisher name.");
        } else {
        	// Retrieve publisher, and store as a message.
        	Publishers publisher;
        	try {
            	publisher = publishersDao.getPublisherByName(publisherName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + publisherName);
        	
        	List<Publishers> publishers = new ArrayList<Publishers>();
        	publishers.add(publisher);
        	
            req.setAttribute("publishers", publishers);
        }
        
        req.getRequestDispatcher("/PublisherRead.jsp").forward(req, resp);
    }

}
