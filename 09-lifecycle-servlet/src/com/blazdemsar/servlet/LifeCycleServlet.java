package com.blazdemsar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int get = 0, post =0;

   
    public LifeCycleServlet() {
        System.out.println("inside constructor...");
    }
    
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("inside init()...");
    }
    
    /*
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("inside service()...");
    }
    */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside doGet()..." + ++get);
		response.setContentType("text/html");
		response.getWriter().append("<strong>" + get + "</strong> counts. Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		out.println("<br>Name : " + request.getParameter("name"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		++post;
		System.out.println("inside doPost()... name:" + name + " Http post was called: " + post + " times.");

		doGet(request, response);
	}

}
