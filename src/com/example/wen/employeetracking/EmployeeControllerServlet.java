package com.example.wen.employeetracking;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/webemployee_tracker")
	private DataSource dataSource;
	private EmployeeJDBCUtils employeeJDBCUtils;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//new utils passing data source
		employeeJDBCUtils = new EmployeeJDBCUtils(dataSource);
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getParameter("command");
		if (command == null){
			command = "LIST";
		}
		switch(command){
		case "LIST":
			showEmployeeList(req, resp);
			break;
		case "ADD":
			addEmployee(req, resp);
			showEmployeeList(req, resp);
			break;
		case "UPDATE":			
			updateEmployee(req, resp);
			showEmployeeList(req, resp);
			break;
		case "LOAD":
			getEmployee(req, resp);
			break;
		case "DELETE":
			deleteEmployee(req, resp);
			showEmployeeList(req, resp);
			break;
		default:
			showEmployeeList(req, resp);
			
		}
		
					
		
		
	}
	private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("employeeid"));
		employeeJDBCUtils.deleteEmployee(id);
		
	}
	private void getEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("employeeid"));
		Employee employee = employeeJDBCUtils.getEmployee(id);
		req.setAttribute("currentEmployee", employee);
		RequestDispatcher dispatcher = req.getRequestDispatcher("update-employee.jsp");		
		dispatcher.forward(req, resp);
		
	}
	private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("employeeid"));
		Employee employee = new Employee(req.getParameter("firstName"),req.getParameter("lastName"),req.getParameter("company"));
		employeeJDBCUtils.updateEmployee(id,employee);
		
	}
	private void addEmployee(HttpServletRequest req, HttpServletResponse resp) {
		Employee employee = new Employee(req.getParameter("firstName"),req.getParameter("lastName"),req.getParameter("company"));
		employeeJDBCUtils.addEmployee(employee);		
	}
	private void showEmployeeList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ArrayList<Employee> employees = new ArrayList<>();
		employees = employeeJDBCUtils.getEmployeeList();
		req.setAttribute("employees", employees);
		RequestDispatcher dispatcher = req.getRequestDispatcher("employee-list.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
	//employee-list.jsp
	

}
