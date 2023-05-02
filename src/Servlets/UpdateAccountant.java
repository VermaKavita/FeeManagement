package Servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/UpdateAccountant")
public class UpdateAccountant extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String design=request.getParameter("design");
		String quali=request.getParameter("quali");
		String pwd=request.getParameter("pwd");
		RequestDispatcher rd = request.getRequestDispatcher("adminhome.html");
		rd.include(request, response);
		out.println("<center><button class='btn btn-info'>Accountant Details</button></center>");
		out.println("<form action='finalUpdate'><table class='table table-primary table-striped table-hover'>");
		out.println("<tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Password</th><td>Qualification</td><td>Designation</td><td colspan=2>Action</td></tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<input type='text'name='fname' value='"+fname+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='lname' value='"+lname+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='email' readonly value='"+email+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='pwd' value='"+pwd+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='quali' value='"+quali+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='design' value='"+design+"'>");	
			out.println("</td>");
			out.println("<td>");
			out.println("<button>Update</button>");		
			out.println("</td>");
			out.println("</tr>");
			out.println("</table></form>");
	}
}


