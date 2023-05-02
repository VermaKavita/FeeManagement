package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		session.setAttribute("email", "email");
	
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		String l=request.getParameter("user");
		session.setAttribute("fname", email);
		if(l.equals("admin")){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance","root","kavita");
					String qr="select * from admin where email=? and pwd=?";
					PreparedStatement ps=con.prepareStatement(qr);
					ps.setString(1, email);
					ps.setString(2,pwd);
				
					ResultSet rs=ps.executeQuery();
					if(rs.next()){
						response.sendRedirect("adminhome.html");
					}else
					{
						RequestDispatcher rd=request.getRequestDispatcher("index.html");
						rd.include(request, response);
						out.println("<script>window.alert('invalid id and password');</script>");
					}
			}
				catch(Exception e)
				{
					e.printStackTrace();
					}
		}
		else if(l.equals("accountant")){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance","root","kavita");		
				String qr="select * from accountant where email=? and pwd=?";
				PreparedStatement ps=con.prepareStatement(qr);
				ps.setString(1, email);
				ps.setString(2,pwd);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					response.sendRedirect("accountant.html");
				}else
				{
					RequestDispatcher rd=request.getRequestDispatcher("index.html");
					rd.include(request, response);
					out.println("<script>window.alert('invalid id and password');</script>");
				}
			}
		
		catch(Exception e){
			out.println("<script>window.alert('Check connection');</script>");
			}
		}
	}
	}


