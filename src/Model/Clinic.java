package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int id;
	String name;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public Clinic(int id, String name) {

		super();
		this.id = id;
		this.name = name;

	}

	public Clinic() {
	}

	public ArrayList<Clinic> getDoctorPolList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");
			while (rs.next()) {
				obj = new Clinic(rs.getInt("id"), rs.getString("name"));
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

	public ArrayList<User> getPolDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			Connection con = conn.ConnDb();
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id,u.tcno,u.name,u.password,u.type FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id="
							+ clinic_id);
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.password"),
						rs.getString("u.name"), rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;

	}

	public boolean addClinic(String name) throws SQLException {

		String query = "INSERT INTO clinic" + "(name) VALUES" + "(?)";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
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

	public boolean uptadeClinic(int id, String name) throws SQLException {

		String query = "UPDATE  clinic SET name=? WHERE id = ?";
		boolean key = false;
		Connection con = conn.ConnDb();
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);

			ps.setString(1, name);
			ps.setInt(2, id);
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

	public boolean delClinic(int id) throws SQLException {

		String query = "DELETE FROM clinic WHERE ID = ?";
		boolean key = false;
		Connection con = conn.ConnDb();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
