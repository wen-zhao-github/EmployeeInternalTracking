package com.example.wen.employeetracking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/EmployeeTestServlet")
public class TestEmployeeJDBCUtilsServlet extends HttpServlet {
	//using resource config in context.xml
	@Resource(name = "jdbc/webemployee_tracker")
	private DataSource dataSource;
	public TestEmployeeJDBCUtilsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//EmployeeJDBCUtils test = new EmployeeJDBCUtils(dataSource);
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet result = null;
		String query = "SELECT * FROM webemployee_tracker.employee;";
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		try {
			//ArrayList<Employee> employees = test.getEmployeeList();
			//Class.forName("com.mysql.jdbc.Driver");
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			result = stat.executeQuery(query);	
						
			out.print("<html><body>");
			/*for (Employee temp : employees) {
				out.print(temp);
			}*/
			while (result.next()){
				//out.print(new Employee(result.getString("first_name"),result.getString("last_name"),result.getString("company")));
				out.print(result.getString("first_name"));
			}
			out.print("<h2> hahahahah</h2>");
			out.print("</body></html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}
