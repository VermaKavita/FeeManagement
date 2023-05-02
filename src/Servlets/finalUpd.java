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

import Bean.Accountant;
import Daos.AccountantDao;

@WebServlet("/finalUpdate")
public class finalUpd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String design = request.getParameter("design");
		String quali = request.getParameter("quali");
		String pwd = request.getParameter("pwd");

		Accountant ac = new Accountant();
		ac.setEmail(email);
		ac.setFname(fname);
		ac.setLname(lname);
		ac.setPwd(pwd);
		ac.setQuali(quali);
		ac.setDesign(design);

		AccountantDao dao = new AccountantDao();
		try {
			dao.updateAccountant(ac);
			RequestDispatcher rd = request.getRequestDispatcher("adminhome.html");
			rd.include(request, response);
			out.println("<center><h1>Updated Successfully</h1></center>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
