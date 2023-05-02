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

import Bean.Student;
import Daos.StudentDao;

@WebServlet("/DeleteStu")
public class DeleteStu extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		Student s=new Student();
		s.setId(id);
		StudentDao sd=new StudentDao();
		try {
			int i=sd.deleteStu(s);
			RequestDispatcher r=request.getRequestDispatcher("accountant.html");
			r.include(request, response);
			if(i>0)
			{
				out.println("<center><h3>Student sucessfully DELETED</h3></center>");
			}
			else
			{
				out.println("OOps ! Something went wrong");
			}
			sd.close();
		} catch (ClassNotFoundException | SQLException e) {
			out.println(e);
		}
	}

}
