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

@WebServlet("/Loan")
public class Loan extends HttpServlet {
	private Connection con;

	private PreparedStatement pstmt;

	private ResultSet resultSet;

	@Override

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession hs = req.getSession();

       String l_type = req.getParameter("l_type");

		String url = "jdbc:mysql://localhost:3306/Bankingapplication";

		String user = "root";

		String pwd = "M@n@s@123";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement("Select * from bank_loan where l_type=?");
			pstmt.setString(1, "l_type");

			ResultSet resultset = pstmt.executeQuery();
			if (resultset.next() == true) {
				hs.setAttribute("lid", resultset.getInt("lid"));
				hs.setAttribute("l_type", resultset.getString("l_type"));
				hs.setAttribute("tenure", resultset.getInt("tenure"));
				hs.setAttribute("interest", resultset.getFloat("interest"));
				hs.setAttribute("description", resultset.getString("description"));

				resp.sendRedirect("/BankingApplication/LoanDetails.jsp");

			}

			else {

				resp.sendRedirect("/BankingApplication/LoanFail.html");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
