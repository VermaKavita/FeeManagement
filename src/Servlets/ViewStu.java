package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Student;
import Daos.StudentDao;

@WebServlet("/ViewStu")
public class ViewStu extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		StudentDao sd = new StudentDao();
		try {
			List<Student> list = sd.show();
			RequestDispatcher r = request.getRequestDispatcher("accountant.html");
			r.include(request, response);
			out.println("<center><button class='btn btn-info'>Student Details</button></center>");
			out.println("<table  class='table table-primary table-striped table-hover text-center'>");
			out.println(
					"<tr><th>Id</th><th>Student Name</th><th>Father Name</th><th>Mother Name</th><th>Fee Status</th><th>Contact</th><th colspan=2>Action</th></tr>");
			for (Student i : list) {
				out.println("<tr>");
				out.println("<td>");
				out.println(i.getId());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getName());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getFname());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getMname());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getFee());
				out.println("</td>");
				out.println("<td>");
				out.println(i.getContact());
				out.println("</td>");
				out.println("<td>");
				out.println("<a href='DeleteStu?id=" + i.getId() + "'>Delete</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("<a href='UpdateStudent?id=" + i.getId() + "&name=" + i.getName() + "&fname="
						+ i.getFname() + "&mname=" + i.getMname() + "&fee=" + i.getFee() + "&contact=" + i.getContact()
						+ "'>Update</a>");
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");

		} catch (ClassNotFoundException | SQLException e) {
			out.println(e);
		}
	}

}
