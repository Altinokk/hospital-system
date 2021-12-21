package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Patient extends User {

	Connection con = conn.ConnDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public Patient() {
	}

	public Patient(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);

	}

	public boolean addPatient(String tcno, String password, String name, String telno, String email)
			throws SQLException {

		int key = 0;
		int count = 0;
		String query = "INSERT INTO user" + "( tcno, password,name,type,telno,email)  VALUES" + "(?,?,?,?,?,?)";

		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");
			while (rs.next()) {
				count++;
				Helper.showMsg("Bu TC ile daha önce kayýt oluþturulmuþ !");
				break;
			}
			if (count == 0) {
				ps = con.prepareStatement(query);

				ps.setString(1, tcno);
				ps.setString(2, password);
				ps.setString(3, name);
				ps.setString(4, "hasta");
				ps.setString(5, telno);
				ps.setString(6, email);
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

	public boolean addAppoint(int doctor_id, String doctor_name, int hasta_id, String hasta_name, String app_date)
			throws SQLException {

		int key = 0;
		int count = 0;
		String query = "INSERT INTO appointment" + "( doctor_id, doctor_name,hasta_id,hasta_name,app_date)  VALUES"
				+ "(?,?,?,?,?)";

		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT * FROM appointment WHERE app_date = '" + app_date + "'");
			while (rs.next()) {
				count++;
				Helper.showMsg("Bu TC ile daha önce kayýt oluþturulmuþ !");
				break;
			}
			if (count == 0) {
				ps = con.prepareStatement(query);

				ps.setInt(1, doctor_id);
				ps.setString(2, doctor_name);
				ps.setInt(3, hasta_id);
				ps.setString(4, hasta_name);
				ps.setString(5, app_date);

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

	public boolean updateWhourStatus(int doctor_id, String wdate) throws SQLException {

		int key = 0;

		String query = "Update whour SET status = ? WHERE doctor_id = ? AND wdate = ? ";

		try {
			ps = con.prepareStatement(query);
			ps.setString(1, "P");
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
	//////////////////////////////////

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

	////////////////////////
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

}
