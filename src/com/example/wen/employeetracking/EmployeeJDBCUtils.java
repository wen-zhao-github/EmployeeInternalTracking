package com.example.wen.employeetracking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class EmployeeJDBCUtils {
	//using resource config in context.xml
	@Resource(name = "jdbc/webemployee_tracker")
	private DataSource dataSource;

	public EmployeeJDBCUtils(DataSource dataSource){		
		this.dataSource = dataSource;
	}
	
	public ArrayList<Employee> getEmployeeList(){
		ArrayList<Employee> employees = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet result = null;
		String query = "SELECT * FROM webemployee_tracker.employee";
		
		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			result = stat.executeQuery(query);
			while(result.next()){
				employees.add(new Employee(result.getString("first_name"),result.getString("last_name"),result.getString("company"),result.getInt("id")));
			}
			for (Employee temp: employees){
				System.out.println(temp);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jDBCCleanup(conn, stat, result);
        }			
		
		
		return employees;
	}

	private void jDBCCleanup(Connection conn, Statement stat, ResultSet result){
		if (result != null){
			try {
				conn.close();
				stat.close();
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stat = null;
		String query = "INSERT INTO webemployee_tracker.employee (first_name,last_name,company) VALUES(?,?,?)";
		
		try {
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(query);
			stat.setString(1, employee.getFirstName());
			stat.setString(2, employee.getLastName());
			stat.setString(3, employee.getCompany());
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jDBCCleanup(conn,stat,null);
	}

	public Employee getEmployee(int id) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		Employee current =null;
		String query = "SELECT * FROM webemployee_tracker.employee WHERE id = ?";
		
		try {
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(query);
			stat.setInt(1, id);
			result = stat.executeQuery();
			while(result.next()){
				current = new Employee(result.getString("first_name"),result.getString("last_name"),result.getString("company"),result.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jDBCCleanup(conn,stat,result);
		return current;
	}

	public void updateEmployee(int id, Employee employee) {
		Connection conn = null;
		PreparedStatement stat = null;
		String query = "UPDATE webemployee_tracker.employee SET first_name = ?, last_name = ?, company = ? WHERE id = ?";
		
		try {
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(query);
			stat.setString(1, employee.getFirstName());
			stat.setString(2, employee.getLastName());
			stat.setString(3, employee.getCompany());
			stat.setInt(4,id);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jDBCCleanup(conn,stat,null);
		
	}

	public void deleteEmployee(int id) {
		Connection conn = null;
		PreparedStatement stat = null;
	    String query = "DELETE FROM webemployee_tracker.employee WHERE id = ?";
	    
	    try {
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(query);
			stat.setInt(1, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    jDBCCleanup(conn,stat,null);
		
	}
	

}
