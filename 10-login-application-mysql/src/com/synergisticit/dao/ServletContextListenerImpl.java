package com.synergisticit.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListenerImpl
 *
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServletContextListenerImpl() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	Connection con = (Connection)sce.getServletContext().getAttribute("DBConnection");
    	try {
    		con.close();
    	}catch(SQLException  e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		String driver = servletContext.getInitParameter("driver");
		String url = servletContext.getInitParameter("url");
		String username = servletContext.getInitParameter("username");
		String password= servletContext.getInitParameter("password");
		try {
			ConnectionManager connectionManager = new ConnectionManager(driver, url, username, password);
			servletContext.setAttribute("DBConnection", connectionManager.getConnection());
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
	}
	
}
