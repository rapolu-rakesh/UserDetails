package com.javaDeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistraitionServlet
 */
@WebServlet("/RegistraitionServlet")
public class RegistraitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		
//		PrintWriter writer = response.getWriter();
//		writer.print(uname);
//		writer.print(uemail);
//		writer.print(upwd);
//		writer.print(umobile);
		
		RequestDispatcher dispatcher = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=12345");
			System.out.println("Connection established...");
			
			PreparedStatement statement = connection.prepareStatement("insert into tejm33.users (uname, upwd, uemail, umobile) values (?,?,?,?)");
			System.out.println("Plot form created...");
			statement.setString(1, uname);
			statement.setString(2, upwd);
			statement.setString(3, uemail);
			statement.setString(4, umobile);
			
			int rowCount = statement.executeUpdate();
			
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowCount>0) {
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}



















