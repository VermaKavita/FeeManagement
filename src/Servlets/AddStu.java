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

import Bean.Student;
import Daos.StudentDao;

@WebServlet("/Addstu")
public class AddStu extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		session.getAttribute("email");
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
		if ((name.equals("")) || fname.equals("") || mname.equals("") || fee.equals("") || id==0) {
			response.sendRedirect("accountant.html");
		}
		Student s=new Student();
		s.setName(name);
		s.setFname(fname);
		s.setMname(mname);
		s.setContact(contact);
		s.setFee(fee);
		s.setId(id);
		StudentDao sd=new StudentDao();
		try {
			int i=sd.addStu(s);
			RequestDispatcher r=request.getRequestDispatcher("accountant.html");
			r.include(request, response);
			if(i>0)
			{
				out.println("<center><h3>Student sucessfully added</h3></center>");
			}
			else
			{
				out.println("OOps ! Something went wrong");
			}
			sd.close();
		} catch (Exception e) {
			out.println(e);
		}
		
	}

}
