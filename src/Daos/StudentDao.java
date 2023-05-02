package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Bean.Student;

public class StudentDao {

	Connection con;

	public Connection getCon() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/advance", "root", "kavita");
	}

	public int addStu(Student s) throws SQLException, ClassNotFoundException {
		con = getCon();
		String qr = "insert into student values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(qr);
		ps.setString(1, s.getName());
		ps.setString(2, s.getFname());
		ps.setString(3, s.getMname());
		ps.setString(4, s.getContact());
		ps.setString(5, s.getFee());
		ps.setInt(6, s.getId());
		return ps.executeUpdate();
	}

	public int updateStu(Student s) throws ClassNotFoundException, SQLException {
		con = getCon();
		String qr = "update student set name=?,fname=?,mname=?,contact=?,fee=? where id=?";
		PreparedStatement ps = con.prepareStatement(qr);
		ps.setString(1, s.getName());
		ps.setString(2, s.getFname());
		ps.setString(3, s.getMname());
		ps.setString(4, s.getContact());
		ps.setString(5, s.getFee());
		ps.setInt(6, s.getId());
		int i = ps.executeUpdate();
		con.close();
		return i;
	}

	public List<Student> show() throws ClassNotFoundException, SQLException {
		con = getCon();
		String qr = "select * from student";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(qr);
		List<Student> al = new ArrayList<Student>();
		while (rs.next()) {
			Student p = new Student();
			p.setName(rs.getString("name"));
			p.setFname(rs.getString("fname"));
			p.setMname(rs.getString("mname"));
			p.setContact(rs.getString("contact"));
			p.setFee(rs.getString("fee"));
			p.setId(rs.getInt("id"));
			al.add(p);
		}
		return al;
	}

	public int deleteStu(Student s) throws ClassNotFoundException, SQLException {
		con = getCon();
		String qr = "delete from student where id=?";
		PreparedStatement ps = con.prepareStatement(qr);
		ps.setInt(1, s.getId());
		int i = ps.executeUpdate();
		return i;
	}

	public void close() throws SQLException {
		con.close();
	}
}
