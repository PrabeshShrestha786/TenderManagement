package com.hit.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hit.beans.VendorBean;
import com.hit.utility.DBUtil;

/**
 * Servlet implementation class LoginSrv This servlet handles login
 * functionality for both Admin and Vendor users.
 */
@WebServlet("/LoginSrv")
public class LoginSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public LoginSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles HTTP GET requests. Redirects GET requests to doPost() method for
	 * unified processing.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Handles HTTP POST requests. Performs login validation for both Admin and
	 * Vendor users.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// Retrieve form input parameters
		String uname = request.getParameter("username").trim();
		String pword = request.getParameter("password").trim();
		String user = request.getParameter("user").trim();

		// Check if user selected "Login as Admin"
		if (user.toLowerCase().equals("login as admin")) {
			// Admin login check
			if (uname.equals("Admin") && pword.equals("Admin")) {
				// login successful
				HttpSession session = request.getSession();
				session.setAttribute("user", "admin");
				session.setAttribute("username", uname);
				session.setAttribute("password", pword);

				// Forward to admin home page
				RequestDispatcher rd = request.getRequestDispatcher("adminHome.jsp");
				rd.forward(request, response);
			} else {
				// Invalid admin credentials → Show error message
				PrintWriter pw = response.getWriter();
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
				pw.print(
						"<script>document.getElementById('show').innerHTML = 'Invalid Username or Password!!'</script>");
			}

		}
		// Check if user selected "Login as Vendor"
		else if (user.toLowerCase().equals("login as vendor")) {
			// Database-related objects
			Connection conn = DBUtil.provideConnection();
			PreparedStatement ps = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			ResultSet rs1 = null;

			try {
				// Step 1: Try to match Vendor by vendor ID and password
				pst = conn.prepareStatement("select * from vendor where vid=? and password=?");
				pst.setString(1, uname);
				pst.setString(2, pword);
				rs = pst.executeQuery();
				if (rs.next()) {
					// Vendor login successful using VID

					// Create session and store user info

					HttpSession session = request.getSession();
					session.setAttribute("user", "user");
					session.setAttribute("username", uname);
					session.setAttribute("password", pword);

					// Fetch vendor details from DB
					String vid = uname;
					String pass = pword;
					String vname = rs.getString("vname");
					String vemail = rs.getString("vemail");
					String vaddr = rs.getString("address");
					String cname = rs.getString("company");
					String mob = rs.getString("vmob");

					// Create VendorBean object to store vendor details
					VendorBean vendor = new VendorBean(vid, vname, mob, vemail, vaddr, cname, pass);

					// Store vendor data in session (used throughout the session)
					session.setAttribute("vendordata", vendor);

					// Redirect to vendor home page
					RequestDispatcher rd = request.getRequestDispatcher("vendorHome.jsp");
					rd.forward(request, response);

				} else {

					// Step 2: If login by VID failed, try login by email and password
					ps = conn.prepareStatement("select * from vendor where vemail=? and password=?");
					ps.setString(1, uname);
					ps.setString(2, pword);

					rs1 = ps.executeQuery();
					if (rs1.next()) {

						// Vendor login successful using email
						HttpSession session = request.getSession();
						session.setAttribute("user", "user");
						session.setAttribute("username", uname);
						session.setAttribute("password", pword);

						// Retrieve vendor details
						String vid = rs1.getString("vid");
						String pass = pword;
						String vname = rs1.getString("vname");
						String vemail = rs1.getString("vemail");
						String vaddr = rs1.getString("address");
						String cname = rs1.getString("company");
						String mob = rs1.getString("vmob");

						// Create vendor bean and store in session
						VendorBean vendor = new VendorBean(vid, vname, mob, vemail, vaddr, cname, pass);
						session.setAttribute("vendordata", vendor); // We need the user data whole the session

						// Redirect to vendor home page
						RequestDispatcher rd = request.getRequestDispatcher("vendorHome.jsp");
						rd.forward(request, response);
					} else {

						// Invalid Vendor credentials → Show error message
						PrintWriter pw = response.getWriter();
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.include(request, response);
						pw.print(
								"<script>document.getElementById('show').innerHTML = 'Invalid Username or Password<br>Please Try Again!'</script>");
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// Handle SQL-related exceptions
				e.printStackTrace();
			} finally {

				// Close database resources safely
				DBUtil.closeConnection(ps);
				DBUtil.closeConnection(pst);
				DBUtil.closeConnection(rs);
				DBUtil.closeConnection(rs1);
			}

		}

	}

}
