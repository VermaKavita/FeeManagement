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

@WebServlet("/finalStuUpd")
public class finalStuUpd extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("sname");
		String fee=request.getParameter("fee");
		String fname=request.getParameter("fname");
		String mname=request.getParameter("mname");
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		String contact =request.getParameter("contact");
		Student s=new Student();
		s.setName(name);
		s.setContact(contact);
		s.setFee(fee);
		s.setFname(fname);
		s.setId(id);
		s.setMname(mname);
		StudentDao Dao=new StudentDao();
		try {
			Dao.updateStu(s);
			RequestDispatcher rd = request.getRequestDispatcher("accountant.html");
			rd.include(request, response);
			out.println("<center><h1>Updated Successfully</h1></center>");
		} catch (ClassNotFoundException | SQLException e) {
			out.println(e);
		}
	}

}
