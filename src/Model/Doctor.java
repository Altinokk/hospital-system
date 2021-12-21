package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Doctor extends User {
	Connection con = conn.ConnDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		// TODO Auto-generated constructor stub
	}

	public boolean addhour(int doctor_id, String doctor_name, String wdate) throws SQLException {
		int key = 0;
		int count = 0;
		String query = "INSERT INTO whour" + "(doctor_id, doctor_name, wdate)  VALUES" + "(?,?,?)";

		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT * FROM whour WHERE status ='A'  And doctor_id =" + doctor_id
					+ " And wdate= + '" + wdate + "'");
			while (rs.next()) {
				count++;
				Helper.showMsg("Bu Saat için bir kayýt bulunmaktadýr !");
			}
			if (count == 0) {
				ps = con.prepareStatement(query);
				ps.setInt(1, doctor_id);
				ps.setString(2, doctor_name);
				ps.setString(3, wdate);
				ps.executeUpdate();

				key = 1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

	public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();
		Whour obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='A' And doctor_id=" + doctor_id);
			while (rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setWdate(rs.getString("wdate"));
				obj.setStatus(rs.getString("status"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;

	}

	public boolean delAppoint(int id) throws SQLException {

		int key = 0;

		String query = "DELETE FROM appointment WHERE ID = ?";

		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			key = 1;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

	public boolean updateWhourStatusA(int doctor_id, String wdate) throws SQLException {

		int key = 0;

		String query = "Update whour SET status = ? WHERE doctor_id = ? AND wdate = ? ";

		try {
			ps = con.prepareStatement(query);
			ps.setString(1, "A");
			ps.setInt(2, doctor_id);
			ps.setString(3, wdate);

			ps.executeUpdate();
			key = 1;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

	public boolean delWhour(int id) throws SQLException {

		String query = "DELETE FROM whour WHERE ID = ?";
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

}
