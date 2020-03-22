package gameranker.servlet;

import gameranker.dal.PublishersDao;
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


@WebServlet("/publisherupdate")
public class PublisherUpdate extends HttpServlet {
	
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

        // Retrieve publisher and validate.
        String publisherId = req.getParameter("publisherid");
        if (publisherId == null) {
            messages.put("success", "Please enter a valid Publisher Id.");
        } else {
        	try {
        		Publishers publisher = publishersDao.getPublisherById(Integer.valueOf(publisherId));
        		if(publisher == null) {
        			messages.put("success", "Publisher does not exist.");
        		}
        		req.setAttribute("publisherid", publisher);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PublisherUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve a publisher and validate.
        String oldPublisherName = req.getParameter("oldpublishername");
        if (oldPublisherName == null) {
            messages.put("success", "Please enter a valid publisherId.");
        } else {
        	try {
        		Publishers publisher = publishersDao.getPublisherByName(oldPublisherName); 
        		if(publisher == null) {
        			messages.put("success", "publisher does not exist. No update to perform.");
        		} else {
        			String newPublisherName = req.getParameter("newpublishername");
        			if (newPublisherName == null || newPublisherName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Publisher Name.");
        	        } else {
        	        	publisher = publishersDao.updatePublisherName(publisher, newPublisherName);
        	        	messages.put("success", "Successfully changed publisher name to " + publisher.getPublisherName());
        	        }
        		}
        		req.setAttribute("publisherid", publisher);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PublisherUpdate.jsp").forward(req, resp);
    }
}
