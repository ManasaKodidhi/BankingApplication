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

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private Connection con;

	private PreparedStatement pstmt;

	private ResultSet resultSet;

	@Override

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		int accno = (int) session.getAttribute("accno");

		String url = "jdbc:mysql://localhost:3306/bankingapplication";

		String user = "root";

		String pwd = "M@n@s@123";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);

			pstmt = con.prepareStatement("select balance from bank_app where accno=?");

			pstmt.setInt(1, accno);

			resultSet = pstmt.executeQuery();

			if (resultSet.next() == true) {

				session.setAttribute("balance",resultSet.getInt("balance"));

				resp.sendRedirect("/BankingApplication/Balance.jsp");

			}

			else {

				resp.sendRedirect("/BankingApplication/BalanceFail.jsp");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}
}
