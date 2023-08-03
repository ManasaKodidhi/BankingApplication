package com.digit.javatraining;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePin")
public class ChangePin extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ServletRequest session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = "jdbc:mysql://localhost:3306/BankingApplication";
		String user = "root";
		String pwd = "M@n@s@123";

		HttpSession session = req.getSession();
		int opin = (int) session.getAttribute("opin");
		int accno = (int) session.getAttribute("accno");

		int npin =Integer.parseInt((req.getParameter("npin")));
		int cpin =Integer.parseInt((req.getParameter("cpin")));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);

			if (npin == cpin) {
				pstmt = con.prepareStatement("update bank_app set pin=? where accno=? and pin=?");
				pstmt.setInt(1, npin);
				pstmt.setInt(2, accno);
				pstmt.setInt(3, opin);
				int x = pstmt.executeUpdate();
				if (x > 0) {

					resp.sendRedirect("/BankingApplication/ChangePinSuccess.html");

				} else {
					resp.sendRedirect("/BankingApplication/ChangePinFailure.html");
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
