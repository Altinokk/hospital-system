package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasHekim extends User {
	Connection con = conn.ConnDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public BasHekim(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);

	}

	public BasHekim() {

	}

	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type ='doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("password"), rs.getString("name"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;

	}

	public ArrayList<User> getPolDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id,u.tcno,u.name,u.password,u.type FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id="
							+ clinic_id);

			while (rs.next()) {
				obj = new User();
				obj.setId(rs.getInt("u.id"));
				obj.setTcno(rs.getString("u.tcno"));
				obj.setPassword(rs.getString("u.password"));
				obj.setName(rs.getString("u.name"));
				obj.setType(rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;

	}

	public boolean addDoctor(String tcno, String password, String name) throws SQLException {

		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, tcno);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, "doktor");
			ps.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean delDoctor(int id) throws SQLException {

		String query = "DELETE FROM user WHERE ID = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean uptadeDoctor(int id, String tcno, String password, String name) throws SQLException {

		String query = "UPDATE  user SET tcno= ?, password=? ,name=? WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, tcno);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setInt(4, id);
			ps.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean workerDoctor(int user_id, int clinic_id) throws SQLException {

		String query = "INSERT INTO worker" + "(clinic_id,user_id) VALUES" + "(?,?)";
		boolean key = false;

		ps = con.prepareStatement(query);
		ps.setInt(1, clinic_id);
		ps.setInt(2, user_id);
		ps.executeUpdate();

		key = true;

		if (key)
			return true;
		else
			return false;
	}

	public boolean delWorkerDoctor(int user_id) throws SQLException {

		String query = "DELETE FROM worker WHERE user_id = ?";

		boolean key = false;

		ps = con.prepareStatement(query);
		ps.setInt(1, user_id);
		ps.executeUpdate();

		key = true;

		if (key)
			return true;
		else
			return false;
	}
	public ArrayList<Appointment> ggetDoctorList() throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			
			rs = st.executeQuery("SELECT * FROM appointment" );
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaId(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			con.close();
			st.close();
			rs.close();
		}
		return list;
	}
	

}
