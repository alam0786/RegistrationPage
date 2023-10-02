package net.javaguides.employeemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.javaguides.employeemanagement.model.Employee;

public class EmployeeDao {
	public int  registerEmployee(Employee employee) throws ClassNotFoundException{
		String INSERT_USERS_SQL="INSERT INTO users"+"(id,firstName,lastName,username,password,address,contact) VALUES"+"(?,?,?,?,?,?,?);";
		int result=0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arshad?useSSL=false", "root", "root");
				PreparedStatement prepareStatement=con.prepareStatement(INSERT_USERS_SQL)){
			prepareStatement.setInt(1, 1);
			prepareStatement.setString(2, employee.getFirstName());
			prepareStatement.setString(3, employee.getLastName());
			prepareStatement.setString(4, employee.getUserName());
			prepareStatement.setString(5, employee.getPassword());
			prepareStatement.setString(6, employee.getAddress());
			prepareStatement.setString(7, employee.getContact());
			System.out.println(prepareStatement);
			result=prepareStatement.executeUpdate();
			
		}catch(SQLException e) {
			printSQLException(e);
		}
		return result;
	}
	 private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
}
