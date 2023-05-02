package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Accountant;
import Daos.AccountantDao;

@WebServlet("/Addacc")
public class AddAcc extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if ((session.getAttribute("email")) == null) {
			out.println("<script>alert('Loggedout')</script>");
			response.sendRedirect("index.html");
		}
		session.setAttribute("fname", "fname");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String design = request.getParameter("design");
		String quali = request.getParameter("quali");
		if ((fname.equals("")) || lname.equals("") || email.equals("") || pwd.equals("") || design.equals("")) {
			response.sendRedirect("adminhome.html");
		}
		Accountant a = new Accountant();
		a.setFname(fname);
		a.setLname(lname);
		a.setEmail(email);
		a.setDesign(design);
		a.setPwd(pwd);
		a.setQuali(quali);
		AccountantDao ad = new AccountantDao();
		try {
			int i = ad.addEmp(a);
			ad.close();
			if (i > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("adminhome.html");
				rd.include(request, response);
				out.println("<script>window.alert('added')</script>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			response.sendRedirect("addaccountant.html");
			out.println("<script>alert('something went wrong')</script>");
		}
	}
}
