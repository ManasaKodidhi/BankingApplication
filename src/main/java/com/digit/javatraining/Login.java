package com.digit.javatraining;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Login")
public class Login extends HttpServlet{
	private Connection con;

    private PreparedStatement pstmt;

 

    @Override

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int cust_id = Integer.parseInt(req.getParameter("cust_id"));
    	 int pin = Integer.parseInt(req.getParameter("pin"));

    	 String url = "jdbc:mysql://localhost:3306/bankingapplication";

         String user = "root";

         String pwd = "M@n@s@123";
         HttpSession session = req.getSession(true);
         try {

             Class.forName("com.mysql.cj.jdbc.Driver");

             con = DriverManager.getConnection(url, user, pwd);

             

             pstmt = con.prepareStatement("Select * from bank_app where cust_id=? and pin=?");

             pstmt.setInt(1, cust_id);

             pstmt.setInt(2, pin);
             ResultSet resultset= pstmt.executeQuery();
             if(resultset.next()==true) {
            	 session.setAttribute("accno", resultset.getInt("accno"));
            	 session.setAttribute("cust_name", resultset.getString("cust_name"));
            	 session.setAttribute("opin", resultset.getInt("pin"));
            	 resp.sendRedirect("/BankingApplication/Home.jsp");
             }
             else {
            	 resp.sendRedirect("/BankingApplication/LoginFail.html");

            	 
             }
            	 		

            	 
             }

         catch (Exception e) {

             e.printStackTrace();


             }
    }
             






}
