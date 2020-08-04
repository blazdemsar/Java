package com.blazdemsar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blazdemsar.dao.EmployeeDao;
import com.blazdemsar.domain.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	EmployeeDao employeeDao = new EmployeeDao();
	
    public EmployeeServlet() {
        super();
    }
    
    // method=get in html form causes request parameters to appear in the address bar
    // http://localhost:8081/02-ServletApp2-mysql/EmployeeServlet?empId=1&name=Blaz&job=Programmer&salary=95000&action=DISPLAY
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("Action: " + action);
		try {
			switch(action) {
			case "save":
				System.out.println("Inside of 'save' switch...");
				saveEmployee(request, response);
				break;
			case "updateForm":
				updateTheForm(request, response);
				break;
			case "update":
				updateEmployee(request, response);
				break;
			case "delete":
				deleteEmployee(request, response);
			default:
				System.out.println("Inside of 'default' switch...");
				getTheEmployeeForm(request, response);
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String empId = request.getParameter("empId");
		String name = request.getParameter("name");
		String job = request.getParameter("job");
		String salary = request.getParameter("salary");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<br>empId: " + empId + ", name : " + name + ", job : " + job + ", salary : " + salary + ", action=" + action);
	}
	
	// method=post in html form then address bar in browser does not display the request parameters, although we can get them printed in the console
	// http://localhost:8081/02-ServletApp2-mysql/EmployeeServlet
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void getTheEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Get the employee form......");
		
		List<Employee> listOfEmployees = employeeDao.allEmployees();
		request.setAttribute("listOfEmployees", listOfEmployees);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void saveEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int empId = Integer.valueOf(request.getParameter("empId"));
		String name = request.getParameter("name");
		String job = request.getParameter("job");
		int salary = Integer.valueOf(request.getParameter("salary")); // we can use parseInt or valueOf
		
		Employee employee = new Employee(empId, name, job, salary);
		
		employeeDao.saveEmployee(employee);
		
		List<Employee> listOfEmployees = employeeDao.allEmployees();
		request.setAttribute("listOfEmployees", listOfEmployees);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int empId = Integer.valueOf(request.getParameter("empId"));
		
		boolean deleted = employeeDao.deleteEmployee(empId);
		System.out.println("Record Deleted : " + deleted);
		
		List<Employee> listOfEmployees = employeeDao.allEmployees();
		request.setAttribute("listOfEmployees", listOfEmployees);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void updateTheForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int empId = Integer.valueOf(request.getParameter("empId"));
		
		Employee empFromDb = employeeDao.getEmployee(empId);
		
		request.setAttribute("employee", empFromDb);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int empId = Integer.valueOf(request.getParameter("empId"));
		String name = request.getParameter("name");
		String job = request.getParameter("job");
		int salary = Integer.valueOf(request.getParameter("salary"));
		
		Employee employee = new Employee(empId, name, job, salary);
		boolean updated = employeeDao.updateEmployee(employee);
		System.out.println("Record Updated : " + updated);
		
		List<Employee> listOfEmployees = employeeDao.allEmployees();
		request.setAttribute("listOfEmployees", listOfEmployees);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int empId = Integer.valueOf(request.getParameter("empId"));
		String name = request.getParameter("name");
		String job = request.getParameter("job");
		int salary = Integer.valueOf(request.getParameter("salary"));
		
		List<Employee> listOfSearchedEmployees = employeeDao.searchEmployee(empId, name, job, salary);
		request.setAttribute("listOfsearchedEmployees", listOfSearchedEmployees);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
		requestDispatcher.forward(request, response);
	}

}
