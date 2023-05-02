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

@WebServlet("/DeleteAcc")
public class DeleteAcc extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String email=request.getParameter("email");
		Accountant a=new Accountant();
		a.setEmail(email);
		AccountantDao ad=new AccountantDao();
		try {
			int i=ad.delEmp(a);
			RequestDispatcher r=request.getRequestDispatcher("adminhome.html");
			r.include(request, response);
			if(i>0)
			{
				out.println("<center><h3>Student sucessfully DELETED</h3></center>");
			}
			else
			{
				out.println("OOps ! Something went wrong");
			}
			ad.close();
		} catch (ClassNotFoundException | SQLException e) {
			out.println(e);
		}
	}

}
