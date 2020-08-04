package com.blazdemsar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.getWriter().append("<br><br>Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		out.println("<br><br><form action=\"EmployeeServlet\" method=\"post\">");
		out.println("<table><tr><td>EMP ID:</td> <td><input type=\"text\" name=\"empId\" /></td></tr>");
		out.println("<tr><td>Name:</td> <td><input type=\"text\" name=\"name\" /></td></tr>");
		out.println("<tr><td>Job:</td> <td><input type=\"text\" name=\"job\" /></td></tr>");
		out.println("<tr><td>Salary:</td> <td><input type=\"number\" name=\"salary\" /></td></tr>");
		out.println("<tr><td>Date of Birth:</td> <td><input type=\"date\" name=\"dob\" /></td></tr>");
		out.println("<tr><td>Email:</td> <td><input type=\"email\" name=\"email\" /></td></tr></table></form>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside doPost(request, response) of EmployeeServlet");
		System.out.println("Now goint to doGet(request, response) of EmployeeServlet");
		
		String empId = request.getParameter("empId");
		String name = request.getParameter("name");
		String job = request.getParameter("job");
		String salary = request.getParameter("salary");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<br><strong>Request parameters are listed below:</strong>");
		out.println("<br>Employee ID: " + empId);
		out.println("<br>Name: " + name);
		out.println("<br>Job: " + job);
		out.println("<br>Salary: " + salary);
		out.println("<br>Date of Birth: " + dob);
		out.println("<br>Email: " + email);
		
		doGet(request, response);
	}

}
