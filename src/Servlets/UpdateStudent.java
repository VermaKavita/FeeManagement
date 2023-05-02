package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		session.getAttribute("fname");
		if((session.getAttribute("fname"))==null){
			out.println("<script>alert('Loggedout')</script>");
			response.sendRedirect("index.html");
		}
		String name=request.getParameter("sname");
		String fee=request.getParameter("fee");
		String fname=request.getParameter("fname");
		String mname=request.getParameter("mname");
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		String contact =request.getParameter("contact");
		RequestDispatcher rd = request.getRequestDispatcher("accountant.html");
		rd.include(request, response);
		out.println("<center><button class='btn btn-info'>Student Details</button></center>");
		out.println("<form action='finalStuUpd'><table class='table table-primary table-striped table-hover'>");
		out.println("<tr><th>S Id</th><th>Student Name</th><th>Father Name</th><th>Mother Name</th><td>Contact</td><td colspan=2>Action</td></tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<input type='number'name='id' readonly value='"+id+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='name' value='"+name+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='fname' value='"+fname+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='mname' value='"+mname+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text'name='contact'min-length='10' max-length='10' value='"+contact+"'>");
			out.println("</td>");
			out.println("<td>");
			out.println("<button>Update</button>");		
			out.println("</td>");
			out.println("</tr>");
			out.println("</table></form>");
	}

}
