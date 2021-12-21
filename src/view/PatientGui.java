package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Clinic;
import Model.Doctor;
import Model.Patient;
import Model.Whour;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import Helper.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class PatientGui extends JFrame {

	private JPanel contentPane;
	private Clinic clinic = new Clinic();
	private static Patient patient = new Patient();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] dataDoctor = null;
	private JTextField txfDoctor_Name;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel WhourModel;
	private Object[] dataWhour = null;
	private String selDoctorName = null;
	private int selDoctorId = 0;
	private JTable tableapp;
	private DefaultTableModel AppModel;
	private Object[] dataApp = null;
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGui frame = new PatientGui(patient);
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

	public PatientGui(Patient patient) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colName = new Object[2];
		colName[0] = "ID";
		colName[1] = "AD SOYAD";
		doctorModel.setColumnIdentifiers(colName);
		dataDoctor = new Object[2];

		WhourModel = new DefaultTableModel();
		Object[] colWhourName = new Object[2];
		colWhourName[0] = "ID";
		colWhourName[1] = "TARIH";
		WhourModel.setColumnIdentifiers(colWhourName);
		dataWhour = new Object[2];

		AppModel = new DefaultTableModel();
		Object[] colappName = new Object[3];
		colappName[0] = "ID";
		colappName[1] = "AD SOYAD";
		colappName[2] = "Tarih";
		AppModel.setColumnIdentifiers(colappName);
		dataApp = new Object[3];
		for (int i = 0; i < appoint.getHastaList(patient.getId()).size(); i++) {
			dataApp[0] = appoint.getHastaList(patient.getId()).get(i).getId();
			dataApp[1] = appoint.getHastaList(patient.getId()).get(i).getDoctorName();
			dataApp[2] = appoint.getHastaList(patient.getId()).get(i).getAppDate();
			AppModel.addRow(dataApp);

		}
		setTitle("Randevu Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 507);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayýn " + patient.getName());
		lblNewLabel.setBounds(10, 11, 377, 29);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui login = new LoginGui();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(634, 16, 89, 23);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.ORANGE);
		contentPane.add(btnNewButton);

		JTabbedPane w_tabb = new JTabbedPane(JTabbedPane.TOP);
		w_tabb.setBounds(10, 87, 724, 363);
		contentPane.add(w_tabb);

		JPanel w_randevu = new JPanel();
		w_randevu.setBackground(Color.WHITE);
		w_tabb.addTab("Randevu Sistemi", null, w_randevu, null);
		w_randevu.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 243, 297);
		w_randevu.add(scrollPane);

		table_doctor = new JTable(doctorModel);
		scrollPane.setViewportView(table_doctor);
		table_doctor.getColumnModel().getColumn(0).setPreferredWidth(2);

		JComboBox Select_Clinic = new JComboBox();
		Select_Clinic.setBounds(263, 38, 145, 27);
		Select_Clinic.addItem("--Poliklinik Seciniz--");
		for (int i = 0; i < clinic.getDoctorPolList().size(); i++) {
			Select_Clinic.addItem(
					new Item(clinic.getDoctorPolList().get(i).getId(), clinic.getDoctorPolList().get(i).getName()));

		}
		Select_Clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Select_Clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < clinic.getPolDoctorList(item.getKey()).size(); i++) {
							dataDoctor[0] = clinic.getPolDoctorList(item.getKey()).get(i).getId();
							dataDoctor[1] = clinic.getPolDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(dataDoctor);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_randevu.add(Select_Clinic);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setBounds(10, 8, 98, 15);
		w_randevu.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Poliklinik Adý");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1.setBounds(263, 11, 105, 15);
		w_randevu.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_3_1 = new JLabel("Doktor Adý");
		lblNewLabel_1_3_1.setForeground(Color.BLACK);
		lblNewLabel_1_3_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel_1_3_1.setBounds(263, 94, 99, 20);
		w_randevu.add(lblNewLabel_1_3_1);

		JButton btn_Seldoctor = new JButton("Seç");
		btn_Seldoctor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int selRow = table_doctor.getSelectedRow();
				if (selRow >= 0) {
					String selPol = table_doctor.getModel().getValueAt(selRow, 0).toString();
					int selPolId = Integer.parseInt(selPol);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < whour.getWhourList(selPolId).size(); i++) {
							dataWhour[0] = whour.getWhourList(selPolId).get(i).getId();
							dataWhour[1] = whour.getWhourList(selPolId).get(i).getWdate();
							WhourModel.addRow(dataWhour);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(WhourModel);
					selDoctorId = selPolId;
					selDoctorName = table_doctor.getModel().getValueAt(selRow, 1).toString();

				} else {
					Helper.showMsg("Lütfen Bir Doktor Seçiniz");
				}

			}
		});
		btn_Seldoctor.setForeground(Color.BLACK);
		btn_Seldoctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_Seldoctor.setBackground(new Color(0, 139, 139));
		btn_Seldoctor.setBounds(263, 148, 110, 25);
		w_randevu.add(btn_Seldoctor);

		JScrollPane table_Whour = new JScrollPane();
		table_Whour.setBounds(418, 27, 301, 297);
		w_randevu.add(table_Whour);

		table_whour = new JTable(WhourModel);
		table_Whour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(3);

		JLabel lblNewLabel_1_2 = new JLabel("Randevu Saatleri");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNewLabel_1_2.setBackground(Color.BLACK);
		lblNewLabel_1_2.setBounds(426, 8, 173, 15);
		w_randevu.add(lblNewLabel_1_2);

		txfDoctor_Name = new JTextField();
		txfDoctor_Name.setHorizontalAlignment(SwingConstants.LEFT);
		txfDoctor_Name.setForeground(Color.BLACK);
		txfDoctor_Name.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		txfDoctor_Name.setColumns(10);
		txfDoctor_Name.setBounds(263, 117, 118, 20);
		w_randevu.add(txfDoctor_Name);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("Randevu");
		lblNewLabel_1_3_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_3_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel_1_3_1_1.setBounds(263, 195, 99, 20);
		w_randevu.add(lblNewLabel_1_3_1_1);

		JButton btn_Seldoctor_1 = new JButton("Randevu Al");
		btn_Seldoctor_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();

				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = patient.addAppoint(selDoctorId, selDoctorName, patient.getId(),
								patient.getName(), date);
						if (control) {
							Helper.showMsg("succsess");
							patient.updateWhourStatus(selDoctorId, date);
							whourListReflesh(selDoctorId);
							apppointListReflesh(patient.getId());
						} else {
							Helper.showMsg("mistake");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("fill");
				}
			}
		});
		btn_Seldoctor_1.setForeground(Color.BLACK);
		btn_Seldoctor_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_Seldoctor_1.setBackground(Color.ORANGE);
		btn_Seldoctor_1.setBounds(263, 226, 110, 25);
		w_randevu.add(btn_Seldoctor_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabb.addTab("Randevularim", null, panel, null);
		panel.setLayout(null);

		JScrollPane PatientPolList = new JScrollPane();
		PatientPolList.setBounds(0, 38, 709, 286);
		panel.add(PatientPolList);

		tableapp = new JTable(AppModel);
		tableapp.setBackground(Color.WHITE);
		PatientPolList.setViewportView(tableapp);

		JButton Btn_canceled = new JButton("\u0130ptal Et");
		Btn_canceled.setForeground(Color.BLACK);
		Btn_canceled.setBounds(620, 3, 89, 25);
		panel.add(Btn_canceled);
		Btn_canceled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = tableapp.getSelectedRow();

				if (selRow >= 0) {
					String selectRow = tableapp.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					String date = tableapp.getModel().getValueAt(selRow, 2).toString();
					try {
						boolean control = patient.delAppoint(selID);
						if (control) {
							Helper.showMsg("succsess");
							patient.updateWhourStatusA(selDoctorId, date);
							whourListReflesh(selDoctorId);
							apppointReflesh(patient.getId());
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
		Btn_canceled.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		Btn_canceled.setBackground(Color.RED);
		tableapp.getColumnModel().getColumn(0).setPreferredWidth(5);

		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					txfDoctor_Name.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString());

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

		});
	}

	public void whourListReflesh(int doctor_id) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) table_whour.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			dataWhour[0] = whour.getWhourList(doctor_id).get(i).getId();
			dataWhour[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			WhourModel.addRow(dataWhour);
		}
	}

	public void apppointListReflesh(int hasta_id) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) table_whour.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < appoint.getHastaList(hasta_id).size(); i++) {
			dataApp[0] = appoint.getHastaList(hasta_id).get(i).getId();
			dataApp[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
			dataApp[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			AppModel.addRow(dataApp);
		}
	}

	public void apppointReflesh(int hasta_id) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) tableapp.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < appoint.getHastaList(hasta_id).size(); i++) {
			dataApp[0] = appoint.getHastaList(hasta_id).get(i).getId();
			dataApp[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
			dataApp[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			AppModel.addRow(dataApp);
		}
	}
}
