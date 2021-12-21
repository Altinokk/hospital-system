package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Doctor;
import Model.Patient;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;

import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JCalendar;

public class DoctorGui extends JFrame {

	private JPanel contentPane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private JTable doctorrandlist;
	private DefaultTableModel doctorModel;
	private Object[] datadoctor = null;
	private Appointment appoint = new Appointment();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGui frame = new DoctorGui(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public DoctorGui(Doctor doctor) throws SQLException {
		whourModel = new DefaultTableModel();
		Object[] colWhourName = new Object[2];
		colWhourName[0] = "ID";
		colWhourName[1] = "Randevu Tarihi ";
		whourModel.setColumnIdentifiers(colWhourName);
		whourData = new Object[2];
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();

			whourModel.addRow(whourData);
		}
		doctorModel = new DefaultTableModel();
		Object[] colappName = new Object[3];
		colappName[0] = "ID";
		colappName[1] = "AD SOYAD";
		colappName[2] = "Tarih";
		doctorModel.setColumnIdentifiers(colappName);
		datadoctor = new Object[3];
		for (int i = 0; i < appoint.getDoctorList(doctor.getId()).size(); i++) {
			datadoctor[0] = appoint.getDoctorList(doctor.getId()).get(i).getId();
			datadoctor[1] = appoint.getDoctorList(doctor.getId()).get(i).getHastaName();
			datadoctor[2] = appoint.getDoctorList(doctor.getId()).get(i).getAppDate();
			doctorModel.addRow(datadoctor);
		}

		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayýn " + doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 377, 29);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui login = new LoginGui();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(627, 17, 89, 23);
		contentPane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 97, 724, 363);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Çalýþma Saatleri", null, panel, null);
		panel.setLayout(null);

		JDateChooser sel_date = new JDateChooser();
		sel_date.setBounds(10, 11, 120, 20);
		panel.add(sel_date);

		JComboBox sel_time = new JComboBox();
		sel_time.setBackground(Color.WHITE);
		sel_time.setModel(new DefaultComboBoxModel(new String[] { "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
				"11:00", "11:30", "12:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00" }));
		sel_time.setBounds(140, 11, 70, 20);
		panel.add(sel_time);

		JButton btn_whouradd = new JButton("Ekle");
		btn_whouradd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				String date = "";

				try {
					date = sdf.format(sel_date.getDate());
				} catch (Exception e2) {
				}

				if (date.length() == 0) {
					Helper.showMsg("Lütfen Bir Tarih ve Saati Seçiniz !");
				} else {
					String time = " " + sel_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						boolean control = doctor.addhour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Helper.showMsg("succsess");
							whourListReflesh(doctor);
						} else {
							Helper.showMsg("mistake");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}

				}
			}
		});

		btn_whouradd.setForeground(new Color(0, 0, 0));
		btn_whouradd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_whouradd.setBackground(new Color(95, 158, 160));
		btn_whouradd.setBounds(220, 11, 70, 20);
		panel.add(btn_whouradd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 42, 719, 293);
		panel.add(scrollPane);

		table_whour = new JTable(whourModel);
		table_whour.setBackground(Color.WHITE);
		scrollPane.setViewportView(table_whour);

		JButton btn_whour_Del = new JButton("Sil");
		btn_whour_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					try {
						boolean control = doctor.delWhour(selID);
						if (control) {
							Helper.showMsg("succsess");
							whourListReflesh(doctor);

						} else
							Helper.showMsg("mistake");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {

				}

			}
		});
		btn_whour_Del.setForeground(Color.BLACK);
		btn_whour_Del.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_whour_Del.setBackground(Color.RED);
		btn_whour_Del.setBounds(615, 11, 89, 23);
		panel.add(btn_whour_Del);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevularim", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 33, 719, 302);
		panel_1.add(scrollPane_1);

		doctorrandlist = new JTable(doctorModel);
		scrollPane_1.setViewportView(doctorrandlist);

		JButton Btn_canceled = new JButton("Ýptal Et");
		Btn_canceled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = doctorrandlist.getSelectedRow();

				if (selRow >= 0) {
					String selectRow = doctorrandlist.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					try {
						boolean control = doctor.delAppoint(selID);
						if (control) {
							Helper.showMsg("succsess");
							doctor.delWhour(selID);
							swhourListReflesh(selID);
							apppointReflesh(doctor.getId());

						} else {
							Helper.showMsg("mistake");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Bir Randevu Seçmediniz");
				}

			}
		});
		Btn_canceled.setForeground(Color.BLACK);
		Btn_canceled.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		Btn_canceled.setBackground(Color.RED);
		Btn_canceled.setBounds(615, 3, 89, 25);
		panel_1.add(Btn_canceled);
	}

	public void whourListReflesh(Doctor doctor) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) table_whour.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();

			whourModel.addRow(whourData);
		}
	}

	public void apppointListReflesh(int hasta_id) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) doctorrandlist.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < appoint.getDoctorList(hasta_id).size(); i++) {
			datadoctor[0] = appoint.getDoctorList(hasta_id).get(i).getHastaId();
			datadoctor[1] = appoint.getDoctorList(hasta_id).get(i).getHastaName();
			datadoctor[2] = appoint.getDoctorList(hasta_id).get(i).getAppDate();
			doctorModel.addRow(datadoctor);
		}
	}

	public void apppointReflesh(int hasta_id) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) doctorrandlist.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < appoint.getDoctorList(hasta_id).size(); i++) {
			datadoctor[0] = appoint.getDoctorList(hasta_id).get(i).getId();
			datadoctor[1] = appoint.getDoctorList(hasta_id).get(i).getHastaName();
			datadoctor[2] = appoint.getDoctorList(hasta_id).get(i).getAppDate();
			doctorModel.addRow(datadoctor);
		}
	}

	
	public void swhourListReflesh(int patient) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) table_whour.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();

			whourModel.addRow(whourData);
		}
	}
}
