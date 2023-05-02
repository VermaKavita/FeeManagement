package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Accountant;
import Daos.AccountantDao;

@WebServlet("/View")
public class ViewAccountant extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		AccountantDao ad = new AccountantDao();
		try {
			List<Accountant> list = ad.showAcc();
			RequestDispatcher r = request.getRequestDispatcher("adminhome.html");
			r.include(request, response);
			out.println("<center><button class='btn btn-info'>Accountant Details</button></center>");
			out.println("<table class='table table-primary table-striped table-hover text-center'>");
			out.println(
					"<tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Password</th><th>Qualification</th><th>Designation</th><th colspan=2>Action</th></tr>");
			for (Accountant i : list) {
				out.println("<tr>");
				out.println("<td>");
				out.println(i.getFname());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getLname());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getEmail());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getPwd());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getQuali());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getDesign());
				out.println("</td>");
				out.println("<td>");
				out.println("<a href='DeleteAcc?email=" + i.getEmail() + "'>Delete</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("<a href='UpdateAccountant?email=" + i.getEmail() + "&fname=" + i.getFname() + "&lname="
						+ i.getLname() + "&pwd=" + i.getPwd() + "&quali=" + i.getQuali() + "&design=" + i.getDesign()
						+ "'>Update</a>");
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			ad.close();
		} catch (Exception e) {
			out.println(e);
		}
	}

}
