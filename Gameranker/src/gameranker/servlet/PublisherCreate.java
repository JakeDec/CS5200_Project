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


@WebServlet("/publishercreate")
public class PublisherCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/PublisherCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
    	System.out.println("!!!!!!!!!!!");

        // Retrieve and validate name.
        String publisherName = req.getParameter("publisherName");
        if (publisherName == null || publisherName.trim().isEmpty()) {
            messages.put("success", "Invalid Publisher");
        } else {
	        try {
	        	Publishers publisher = new Publishers(publisherName);
	        	publisher = publishersDao.create(publisher);
	        	messages.put("success", "Successfully created " + publisherName);
	        	
	        	System.out.println("!!!!!!!!!!!" + publisherName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PublisherCreate.jsp").forward(req, resp);
    }
}
