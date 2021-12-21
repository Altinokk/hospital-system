package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.BasHekim;
import Model.Doctor;
import Model.Patient;

public class LoginGui extends JFrame {

	private JPanel contentPane;
	private JTextField fld_hasta_tc;
	private JTextField fld_doctor_tc;
	private JPasswordField fld_doctor_pass;
	private JPasswordField fld_hasta_pass;
	private DBConnection Conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGui() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("hastanee.png")));
		lblNewLabel.setBounds(201, 22, 87, 49);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Hastane Giriþ Sistemine Hþgeldiniz");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_1.setBounds(89, 97, 311, 25);
		contentPane.add(lblNewLabel_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 133, 464, 267);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Hasta giriþ", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("T.C Numaranýz :");
		lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		lblNewLabel_2.setBounds(33, 43, 147, 27);
		panel.add(lblNewLabel_2);

		fld_hasta_tc = new JTextField();
		fld_hasta_tc.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		fld_hasta_tc.setBounds(198, 38, 194, 27);
		panel.add(fld_hasta_tc);
		fld_hasta_tc.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Þifreniz :");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(33, 104, 147, 27);
		panel.add(lblNewLabel_2_1);

		JButton btn_login = new JButton("Kayýt Ol");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGui Rgui = new RegisterGui();
				Rgui.setVisible(true);
				dispose();
			}
		});
		btn_login.setForeground(Color.BLACK);
		btn_login.setBackground(Color.ORANGE);
		btn_login.setFont(new Font("Arial", Font.BOLD, 15));
		btn_login.setBounds(46, 166, 147, 46);
		panel.add(btn_login);

		JButton btn_enter = new JButton("Giriþ Yap");
		btn_enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hasta_tc.getText().length() == 0 || fld_hasta_pass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;

					try {
						Connection con = Conn.ConnDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * fROM user");

						while (rs.next()) {
							if (fld_hasta_tc.getText().equals(rs.getString("tcno"))
									&& fld_hasta_pass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									Patient pHekim = new Patient();
									pHekim.setId(rs.getInt("id"));
									pHekim.setPassword("password");
									pHekim.setName(rs.getString("name"));
									pHekim.setTcno(rs.getString("tcno"));
									pHekim.setType(rs.getString("type"));
									PatientGui pGui = new PatientGui(pHekim);
									pGui.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					if (key) {
						Helper.showMsg("T.C. Kimlik numaranýzý veya Þifrenizi hatalý girdiniz.!");
					}
				}

			}
		});
		btn_enter.setForeground(Color.WHITE);
		btn_enter.setBackground(Color.BLUE);
		btn_enter.setFont(new Font("Arial", Font.BOLD, 15));
		btn_enter.setBounds(258, 166, 147, 46);
		panel.add(btn_enter);

		fld_hasta_pass = new JPasswordField();
		fld_hasta_pass.setBounds(198, 105, 194, 27);
		panel.add(fld_hasta_pass);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Giriþi", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("T.C Numaranýz :");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(33, 43, 147, 27);
		panel_1.add(lblNewLabel_2_2);

		fld_doctor_tc = new JTextField();
		fld_doctor_tc.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		fld_doctor_tc.setColumns(10);
		fld_doctor_tc.setBounds(198, 38, 194, 27);
		panel_1.add(fld_doctor_tc);

		JLabel lblNewLabel_2_1_1 = new JLabel("Þifreniz :");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(33, 104, 147, 27);
		panel_1.add(lblNewLabel_2_1_1);

		JButton btn_doctor_enter = new JButton("Giriþ Yap");
		btn_doctor_enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctor_tc.getText().length() == 0 || fld_doctor_pass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {

						Connection con = Conn.ConnDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * fROM user");
						while (rs.next()) {
							if (fld_doctor_tc.getText().equals(rs.getString("tcno"))
									&& fld_doctor_pass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									BasHekim bHekim = new BasHekim();
									bHekim.setId(rs.getInt("id"));
									bHekim.setPassword("password");
									bHekim.setName(rs.getString("name"));
									bHekim.setTcno(rs.getString("tcno"));
									bHekim.setType(rs.getString("type"));
									BasHekimGui bGui = new BasHekimGui(bHekim);
									bGui.setVisible(true);
									dispose();
									key = false;
								}
								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setName(rs.getString("name"));
									doctor.setTcno(rs.getString("tcno"));
									doctor.setType(rs.getString("type"));
									DoctorGui dGui = new DoctorGui(doctor);
									dGui.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					if (key) {
						Helper.showMsg("T.C. Kimlik numaranýzý veya Þifrenizi hatalý girdiniz.!");
					}
				}
			}
		});
		btn_doctor_enter.setForeground(Color.WHITE);
		btn_doctor_enter.setFont(new Font("Arial", Font.BOLD, 15));
		btn_doctor_enter.setBackground(Color.BLUE);
		btn_doctor_enter.setBounds(140, 168, 147, 46);
		panel_1.add(btn_doctor_enter);

		fld_doctor_pass = new JPasswordField();
		fld_doctor_pass.setBounds(198, 105, 194, 27);
		panel_1.add(fld_doctor_pass);

	}
}
