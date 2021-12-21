package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Helper.*;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class BasHekimGui extends JFrame {
	Clinic Clinic = new Clinic();
	static BasHekim bashekim = new BasHekim();
	private JPanel contentPane;
	private JTextField fld_tc;
	private JTextField fld_name;
	private JTextField fld_pass;
	private JTextField fld_id;
	private JTable tbldoctorlist;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable tbl_pol;
	private JTextField txf_PolName;
	private JTable pollist;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JTextField tf_Pol_Id;
	private JTextField tf_list_id;
	private DefaultTableModel workerModel = null;
	private Object[] workerData = null;
	private JTable allList;
	private  DefaultTableModel appointModel = null;
	private Object[] appointData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGui frame = new BasHekimGui(bashekim);
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
	public BasHekimGui(BasHekim basHekim) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "AD SOYAD";
		colDoctorName[2] = "T.C NO";
		colDoctorName[3] = "ÞÝFRE";

		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		clinicModel = new DefaultTableModel();
		Object[] colpolname = new Object[2];
		colpolname[0] = "ID";
		colpolname[1] = "Poliklinkler";

		clinicModel.setColumnIdentifiers(colpolname);
		clinicData = new Object[2];
		for (int i = 0; i < Clinic.getDoctorPolList().size(); i++) {
			clinicData[0] = Clinic.getDoctorPolList().get(i).getId();
			clinicData[1] = Clinic.getDoctorPolList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		// worker Model

		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorkerName = new Object[2];
		colWorkerName[0] = "ID";
		colWorkerName[1] = "AD SOYAD";
		workerModel.setColumnIdentifiers(colWorkerName);
		Object[] workerData = new Object[2];
		

		DefaultTableModel appointModel = new DefaultTableModel();
		Object[] colAppName = new Object[6];
		 colAppName[0] = "ID";
		 colAppName[1] = "Doktor ID";
		 colAppName[2] = "Doktor Adý";
		 colAppName[3] = "Hasta Id";
		 colAppName[4] = "Hasta Adi";
		 colAppName[5] = "Tarih";
		appointModel.setColumnIdentifiers(colAppName);
		 appointData = new Object[6];
		for (int i = 0; i < bashekim.ggetDoctorList().size(); i++) {
			appointData[0] = bashekim.ggetDoctorList().get(i).getId();
			appointData[1] = bashekim.ggetDoctorList().get(i).getDoctorId();
			appointData[2] = bashekim.ggetDoctorList().get(i).getDoctorName();
			appointData[3] = bashekim.ggetDoctorList().get(i).getHastaId();
			appointData[4] = bashekim.ggetDoctorList().get(i).getHastaName();
			appointData[5] = bashekim.ggetDoctorList().get(i).getAppDate();
			appointModel.addRow(appointData);
		
		}
	
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayýn " + basHekim.getName());
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
		btnNewButton.setBounds(627, 17, 89, 23);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		contentPane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 97, 724, 363);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("T.C NO");
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1.setBounds(557, 21, 78, 20);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("AD SOYAD");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(557, 77, 99, 20);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Þifre");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(557, 127, 67, 20);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Kullanici ID");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(557, 236, 99, 20);
		panel.add(lblNewLabel_1_3);

		fld_tc = new JTextField();
		fld_tc.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		fld_tc.setBounds(557, 46, 152, 20);
		panel.add(fld_tc);
		fld_tc.setColumns(10);

		fld_name = new JTextField();
		fld_name.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		fld_name.setColumns(10);
		fld_name.setBounds(557, 102, 152, 20);
		panel.add(fld_name);

		fld_pass = new JTextField();
		fld_pass.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		fld_pass.setColumns(10);
		fld_pass.setBounds(557, 158, 152, 20);
		panel.add(fld_pass);

		fld_id = new JTextField();
		fld_id.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		fld_id.setColumns(10);
		fld_id.setBounds(557, 261, 152, 20);
		panel.add(fld_id);

		JButton btn_ekle = new JButton("Ekle");
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_tc.getText().length() == 0 || fld_name.getText().length() == 0
						|| fld_pass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						boolean control = bashekim.addDoctor(fld_tc.getText(), fld_pass.getText(), fld_name.getText());
						if (control) {
							Helper.showMsg("succsess");
							fld_tc.setText(null);
							fld_name.setText(null);
							fld_pass.setText(null);
							doktorListReflesh();
						}

					} catch (Exception e1) {
						// TODO: handle exception
					}
				}
			}

		});
		btn_ekle.setForeground(new Color(0, 0, 0));
		btn_ekle.setBackground(new Color(95, 158, 160));
		btn_ekle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_ekle.setBounds(580, 195, 99, 30);
		panel.add(btn_ekle);

		JButton btnNewButton_1_1 = new JButton("Sil");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_id.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					if (Helper.askmsg("sure")) {
						int selectId = Integer.parseInt(fld_id.getText());
						try {
							boolean control = bashekim.delDoctor(selectId);
							if (control) {
								Helper.showMsg("succsess");
								doktorListReflesh();
								fld_id.setText(null);

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1.setBackground(new Color(255, 69, 0));
		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(580, 292, 99, 30);
		panel.add(btnNewButton_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 537, 313);
		panel.add(scrollPane);

		tbldoctorlist = new JTable(doctorModel);
		tbldoctorlist.setForeground(Color.BLACK);
		scrollPane.setViewportView(tbldoctorlist);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinik", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollpanepol = new JScrollPane();
		scrollpanepol.setBounds(10, 11, 260, 313);
		panel_1.add(scrollpanepol);

		tbl_pol = new JTable(clinicModel);
		tbl_pol.setForeground(Color.BLACK);
		tbl_pol.setBackground(Color.WHITE);
		scrollpanepol.setViewportView(tbl_pol);

		JLabel lblNewLabel_1_1_1 = new JLabel("AD SOYAD");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(280, 11, 99, 20);
		panel_1.add(lblNewLabel_1_1_1);

		txf_PolName = new JTextField();
		txf_PolName.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		txf_PolName.setColumns(10);
		txf_PolName.setBounds(280, 35, 152, 25);
		panel_1.add(txf_PolName);

		JScrollPane tbl_poldoctor = new JScrollPane();
		tbl_poldoctor.setBounds(440, 11, 260, 313);
		panel_1.add(tbl_poldoctor);

		pollist = new JTable();
		pollist.setForeground(Color.BLACK);
		pollist.setBackground(Color.WHITE);
		tbl_poldoctor.setViewportView(pollist);

		JButton btn_Pol_Add = new JButton("Poliklinik Ekle");
		btn_Pol_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txf_PolName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (Clinic.addClinic(txf_PolName.getText())) {
							Helper.showMsg("succsess");
							txf_PolName.setText(null);
							polReflesh();
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		btn_Pol_Add.setForeground(Color.BLACK);
		btn_Pol_Add.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_Pol_Add.setBackground(new Color(95, 158, 160));
		btn_Pol_Add.setBounds(304, 66, 110, 25);
		panel_1.add(btn_Pol_Add);

		JButton btn_pol_del = new JButton("Poliklinik Sil");
		btn_pol_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_Pol_Id.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					if (Helper.askmsg("sure")) {
						int selectId = Integer.parseInt(tf_Pol_Id.getText());
						try {
							boolean control = Clinic.delClinic(selectId);
							if (control) {
								Helper.showMsg("succsess");
								polReflesh();
								tf_Pol_Id.setText(null);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		btn_pol_del.setForeground(new Color(0, 0, 0));
		btn_pol_del.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_pol_del.setBackground(Color.ORANGE);
		btn_pol_del.setBounds(304, 164, 110, 25);
		panel_1.add(btn_pol_del);

		JLabel lblNewLabel_1_3_1 = new JLabel("Poliklinik ID");
		lblNewLabel_1_3_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel_1_3_1.setBounds(280, 102, 99, 20);
		panel_1.add(lblNewLabel_1_3_1);

		tf_Pol_Id = new JTextField();
		tf_Pol_Id.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		tf_Pol_Id.setColumns(10);
		tf_Pol_Id.setBounds(280, 125, 152, 25);
		panel_1.add(tf_Pol_Id);

		JComboBox sel_doc = new JComboBox();
		sel_doc.setBounds(280, 263, 150, 20);

		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			sel_doc.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));

		}
		sel_doc.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();

		});

		panel_1.add(sel_doc);

		JButton btn_doc_add = new JButton("Ekle");
		btn_doc_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_pol.getSelectedRow();
				if (selRow >= 0) {
					String selPol = tbl_pol.getModel().getValueAt(selRow, 0).toString();
					int selPolId = Integer.parseInt(selPol);
					Item doctoritem = (Item) sel_doc.getSelectedItem();

					try {
						boolean control = bashekim.workerDoctor(doctoritem.getKey(), selPolId);
						if (control) {
							DefaultTableModel clearModel = (DefaultTableModel) pollist.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getPolDoctorList(selPolId).size(); i++) {
								workerData[0] = bashekim.getPolDoctorList(selPolId).get(i).getId();
								workerData[1] = bashekim.getPolDoctorList(selPolId).get(i).getName();
								workerModel.addRow(workerData);
							}
							Helper.showMsg("succsess");

						} else
							Helper.showMsg("mistake");

					} catch (Exception e2) {
						// TODO: handle exception
					}
				} else {
					Helper.showMsg("Lütfen Bir Poliklinik Seçiniz !");
				}
			}
		});
		btn_doc_add.setForeground(new Color(0, 0, 0));
		btn_doc_add.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_doc_add.setBackground(new Color(154, 205, 50));
		btn_doc_add.setBounds(280, 294, 70, 25);
		panel_1.add(btn_doc_add);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("Pol :");
		lblNewLabel_1_3_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel_1_3_1_1.setBounds(280, 200, 27, 20);
		panel_1.add(lblNewLabel_1_3_1_1);

		JButton btn_Pol_Add_1 = new JButton("Listele");
		btn_Pol_Add_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = tbl_pol.getSelectedRow();
				if (selRow >= 0) {
					String selPol = tbl_pol.getModel().getValueAt(selRow, 0).toString();
					int selPolId = Integer.parseInt(selPol);
					DefaultTableModel clearModel = (DefaultTableModel) pollist.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < bashekim.getPolDoctorList(selPolId).size(); i++) {
							workerData[0] = bashekim.getPolDoctorList(selPolId).get(i).getId();
							workerData[1] = bashekim.getPolDoctorList(selPolId).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					pollist.setModel(workerModel);

				} else {
					Helper.showMsg("Lütfen bir poliklink seciniz");
				}
			}
		});
		btn_Pol_Add_1.setForeground(new Color(0, 0, 0));
		btn_Pol_Add_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_Pol_Add_1.setBackground(Color.PINK);
		btn_Pol_Add_1.setBounds(304, 227, 110, 25);
		panel_1.add(btn_Pol_Add_1);

		tf_list_id = new JTextField();
		tf_list_id.setHorizontalAlignment(SwingConstants.LEFT);
		tf_list_id.setForeground(new Color(0, 0, 0));
		tf_list_id.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		tf_list_id.setColumns(10);
		tf_list_id.setBounds(314, 201, 118, 20);
		panel_1.add(tf_list_id);

		JButton btn_doc_add_1 = new JButton("Çýkar");
		btn_doc_add_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_pol.getSelectedRow();
				if (selRow >= 0) {
					String selPol = tbl_pol.getModel().getValueAt(selRow, 0).toString();
					int selPolId = Integer.parseInt(selPol);

					Item doctoritem = (Item) sel_doc.getSelectedItem();

					try {
						boolean control = bashekim.delWorkerDoctor(doctoritem.getKey());
						if (control) {
							DefaultTableModel clearModel = (DefaultTableModel) pollist.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getPolDoctorList(selPolId).size(); i++) {
								workerData[0] = bashekim.getPolDoctorList(selPolId).get(i).getId();
								workerData[1] = bashekim.getPolDoctorList(selPolId).get(i).getName();
								workerModel.addRow(workerData);
							}
							Helper.showMsg("succsess");

						} else
							Helper.showMsg("mistake");

					} catch (Exception e2) {
						// TODO: handle exception
					}
				} else {
					Helper.showMsg("Lütfen Bir Poliklinik Seçiniz !");
				}

			}
		});

		btn_doc_add_1.setForeground(Color.BLACK);
		btn_doc_add_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_doc_add_1.setBackground(Color.RED);
		btn_doc_add_1.setBounds(362, 294, 70, 25);
		panel_1.add(btn_doc_add_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Randevular", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 719, 335);
		panel_2.add(scrollPane_1);
		
		allList = new JTable(appointModel);
		scrollPane_1.setViewportView(allList);
		allList.getColumnModel().getColumn(0).setPreferredWidth(2);
		allList.getColumnModel().getColumn(1).setPreferredWidth(2);
		allList.getColumnModel().getColumn(3).setPreferredWidth(2);
		

		tbldoctorlist.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			
			
			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					fld_id.setText(tbldoctorlist.getValueAt(tbldoctorlist.getSelectedRow(), 0).toString());

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

		});

		tbl_pol.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					tf_Pol_Id.setText(tbl_pol.getValueAt(tbl_pol.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

		});

		tbl_pol.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(tbl_pol.getValueAt(tbl_pol.getSelectedRow(), 0).toString());
					String selectName = (tbl_pol.getValueAt(tbl_pol.getSelectedRow(), 1).toString());

					try {
						Clinic.uptadeClinic(selectID, selectName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}

		});

		tbl_pol.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					tf_list_id.setText(tbl_pol.getValueAt(tbl_pol.getSelectedRow(), 1).toString());

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

		});

		tbldoctorlist.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(tbldoctorlist.getValueAt(tbldoctorlist.getSelectedRow(), 0).toString());
					String selectName = (tbldoctorlist.getValueAt(tbldoctorlist.getSelectedRow(), 1).toString());
					String selectTcno = (tbldoctorlist.getValueAt(tbldoctorlist.getSelectedRow(), 2).toString());
					String selectPass = (tbldoctorlist.getValueAt(tbldoctorlist.getSelectedRow(), 3).toString());

					try {
						bashekim.uptadeDoctor(selectID, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}

		});

	}

	public void doktorListReflesh() throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) tbldoctorlist.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	public void polReflesh() throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) tbl_pol.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < Clinic.getDoctorPolList().size(); i++) {
			clinicData[0] = Clinic.getDoctorPolList().get(i).getId();
			clinicData[1] = Clinic.getDoctorPolList().get(i).getName();

			clinicModel.addRow(clinicData);

		}
	}

	public void pollistReflesh(int id) throws SQLException {
		DefaultTableModel refleshModel = (DefaultTableModel) pollist.getModel();
		refleshModel.setRowCount(0);
		for (int i = 0; i < bashekim.getPolDoctorList(id).size(); i++) {
			workerData[0] = bashekim.getPolDoctorList(id).get(i).getId();
			workerData[1] = bashekim.getPolDoctorList(id).get(i).getName();
			workerModel.addRow(workerData);
		}
	}
	/*for (int i = 0; i < appoint.getDoctorList().size(); i++) {
		appointData[0] = appoint.getDoctorList().get(i).getId();
		appointData[1] = appoint.getDoctorList().get(i).getName();
		appointData[2] = appoint.getDoctorList().get(i).getTcno();
		appointData[3] = appoint.getDoctorList().get(i).getPassword();
		
		appointModel.addRow(appointData);
	}*/
}
