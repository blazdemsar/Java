package com.blazdemsar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* we can have servlet hosted under different names
@WebServlet(
		urlPatterns = {"/LoginServlet", "/loginServlet", "/loginservlet"},
		initParams= {
				@WebInitParam(name="user", value="root"),
				@WebInitParam(name="pwd", value="admin")
		}
		
)
*/
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();

    }


	public void init() throws ServletException {

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("<br>Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = getServletConfig().getInitParameter("user");
		String pwd = getServletConfig().getInitParameter("pwd");
		System.out.println("user: " + user + " pwd: " + pwd);
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/html");
		printWriter.println("<br>Username: " + user);
		printWriter.println("<br>Password: " + pwd);
		
		String dbUser = getServletContext().getInitParameter("dbUser");
		String dbPassword = getServletContext().getInitParameter("dbPassword");
		String dbDriver = getServletContext().getInitParameter("dbDriver");
		String dbUrl = getServletContext().getInitParameter("dbUrl");
		
		printWriter.println("<br>dbUser: " + dbUser + ", dbPassword: " + dbPassword + ", dbDriver: " + dbDriver + ", dbUrl: " + dbUrl);
		
		doGet(request, response);
	}

}
