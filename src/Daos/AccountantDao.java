package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Bean.Accountant;

public class AccountantDao {

	public Connection con;

	public Connection getCon() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/advance", "root", "kavita");
	}

	public int addEmp(Accountant e) throws ClassNotFoundException, SQLException {
		con = getCon();
		String qr = "insert into accountant values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(qr);
		ps.setString(1, e.getFname());
		ps.setString(2, e.getLname());
		ps.setString(3, e.getEmail());
		ps.setString(4, e.getDesign());
		ps.setString(5, e.getQuali());
		ps.setString(6, e.getPwd());
		int i = ps.executeUpdate();
		return i;
	}

	public int delEmp(Accountant e) throws SQLException, ClassNotFoundException {
		con = getCon();
		String qr = "delete from accountant where email=?";
		PreparedStatement ps = con.prepareStatement(qr);
		ps.setString(1, e.getEmail());
		return ps.executeUpdate();
	}

	public List<Accountant> showAcc() throws ClassNotFoundException, SQLException {
		con = getCon();
		String qr = "select * from accountant";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(qr);
		List<Accountant> al = new ArrayList<Accountant>();
		while (rs.next()) {
			Accountant p = new Accountant();
			p.setFname(rs.getString("fname"));
			p.setLname(rs.getString("lname"));
			p.setEmail(rs.getString("email"));
			p.setDesign(rs.getString("design"));
			p.setQuali(rs.getString("quali"));
			p.setPwd(rs.getString("pwd"));
			al.add(p);
		}
		return al;
	}

	public int updateAccountant(Accountant a) throws ClassNotFoundException, SQLException {
		con = getCon();
		String qr = "update accountant set fname=?,lname=?,design=?,quali=?,pwd=? where email=?";
		PreparedStatement ps = con.prepareStatement(qr);
		ps.setString(1, a.getFname());
		ps.setString(2, a.getLname());
		ps.setString(3, a.getDesign());
		ps.setString(4, a.getQuali());
		ps.setString(5, a.getPwd());
		ps.setString(6, a.getEmail());
		int i = ps.executeUpdate();
		con.close();
		return i;
	}

	public void close() throws SQLException {
		con.close();
	}
}
