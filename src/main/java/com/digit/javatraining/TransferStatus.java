package com.digit.javatraining;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/TransferStatus")
public class TransferStatus extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultset2;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = "jdbc:mysql://localhost:3306/Bankingapplication";

		String user = "root";

		String pwd = "M@n@s@123";
		HttpSession session = req.getSession(true);
		int cust_id = Integer.parseInt(req.getParameter("cust_id"));
		String ifsc_code = req.getParameter("ifsc");
		String rifsc_code = req.getParameter("receiver_ifsc");
		int accno = Integer.parseInt(req.getParameter("sender_accno"));
		int raccno = Integer.parseInt(req.getParameter("receiver_accno"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		String bank_name = req.getParameter("bank_name");
		
          
		Random r = new Random();
	    String transaction_id = (100000 + r.nextInt(900000))+"";
	    session.setAttribute("tid", transaction_id);
        
        
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);

			
			pstmt = con.prepareStatement("Select * from bank_app where cust_id=? and ifsc_code=? and pin=? and accno=?");

			pstmt.setInt(1, cust_id);
			pstmt.setString(2, ifsc_code);
			pstmt.setInt(3, accno);
			pstmt.setInt(4, pin);

			ResultSet resultset1 = pstmt.executeQuery();
			if (resultset1.next() == true) {
				pstmt = con.prepareStatement("Select * from bank_app where ifsc_code=? and accno=?");
				pstmt.setString(1, ifsc_code);
				pstmt.setInt(2, accno);
				ResultSet resultset2 = pstmt.executeQuery();

			if (resultset2.next() == true) {
				pstmt = con.prepareStatement("Select balance from bank_app where accno=?");
				pstmt.setInt(1, accno);
				ResultSet resultset3 = pstmt.executeQuery();
				int balance = resultset3.getInt(1);
				if (balance > amount) {
					pstmt = con.prepareStatement("Update bank_app set balance=balance-? where accno=?");
					pstmt.setInt(1, amount);
					pstmt.setInt(2, accno);
					int x1 = pstmt.executeUpdate();
					if (x1 > 0) {
						pstmt = con.prepareStatement("Update bank_pp set balance=balance+? where accno=?");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, raccno);
						int x2 = pstmt.executeUpdate();
						if (x2 > 0) {
							pstmt = con.prepareStatement("Insert into transfer_status values(?,?,?,?,?,?,?)");
							pstmt.setInt(1, cust_id);
							pstmt.setString(2, bank_name);
							pstmt.setString(3, ifsc_code);
							pstmt.setInt(4, accno);
							pstmt.setString(5, rifsc_code);
							pstmt.setInt(6, raccno);
							pstmt.setInt(7, amount);

							int x3 = pstmt.executeUpdate();
							if (x3 > 0) {
								resp.sendRedirect("/BankingApplication/TransferStatusSuccess.html");
							} else {
								resp.sendRedirect("/BankingApplication/TransferStatusFail.jsp");
							}
						}

						else {
							resp.sendRedirect("/BankingApplication/TransferStatusFail.jsp");
						}
					}

					else {
						resp.sendRedirect("/BankingApplication/TransferStatusFail.jsp");
					}

				} else {
					resp.sendRedirect("/BankingApplication/TransferStatusFail.jsp");

				}
			}
			else {
				resp.sendRedirect("/BankingApplication/TransferStatusSuccess.html");
			}

		}
			else {
				resp.sendRedirect("/BankingApplication/TransferStatusSuccess.html");
			}

		
	}
		catch (Exception e) {

			e.printStackTrace();

		}
}
}
