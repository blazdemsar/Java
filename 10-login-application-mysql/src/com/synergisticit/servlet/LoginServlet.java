package com.synergisticit.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.synergisticit.domain.Employee;
import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.dao.UserDao;
import com.synergisticit.domain.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Connection con = null;
    
    EmployeeDao employeeDao = new EmployeeDao();
    
    public LoginServlet() {
        super();
        
    }

	@Override
	public void init() throws ServletException {
		con = (Connection)getServletContext().getAttribute("DBConnection");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside doGet method...action - " + request.getParameter("action"));
		String action = request.getParameter("action");
		System.out.println("action: "+ action);
		try {
			switch(action) {
			case "register":
				System.out.println("Register user: "+action);
				registerTheUser(request, response);
			case "logout":
				System.out.println("Logout the user: "+action);
			    logoutTheUser(request, response);
			case "allEmployees":
				System.out.println("Listing All Employees: " + action);
				allEmployees(request, response);
			 default:
				System.out.println("default case...");
			    loginTheUser(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside of doPost...action - " + request.getParameter("action"));
		doGet(request, response);
	}
	
	public void loginTheUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		User userFromDb = UserDao.login(user, con);
		
		if(userFromDb.isValid()) {
			HttpSession session = request.getSession();
			session.setAttribute("validUser", userFromDb.isValid());
			session.setAttribute("userFromDb", userFromDb);
			response.sendRedirect("login-success.jsp");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}
		
	}
	
	//registerTheUser
	public void registerTheUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean valid = true;
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String email= request.getParameter("email");
		
		if(name!= null && password != null && email!=null) {
			UserDao.registerTheUser(name, password, email, valid, con);
			request.setAttribute("registration", "You are registered successfully, please login now.");
	         RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	         rd.forward(request, response);
		}
		/*
		else {
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
	        rd.forward(request, response);
		}
		*/
		
		
			
	}

	public void logoutTheUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		request.getSession().invalidate();
        //rd.forward(request, response);
		
	}
	
	public void allEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Get the employee form......");
		
		List<Employee> listOfEmployees = employeeDao.allEmployees(con);
		request.setAttribute("listOfEmployees", listOfEmployees);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
		requestDispatcher.forward(request, response);
	}

}
