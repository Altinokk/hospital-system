package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Patient;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RegisterGui extends JFrame {

	private JPanel contentPane;
	private JTextField txfTcno;
	private JTextField TxfName;
	private JPasswordField passwordField;
	private JTextField txfMail;
	private JTextField txfTelno;
	private Patient patient = new Patient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGui frame = new RegisterGui();
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
	public RegisterGui() {
		setResizable(false);
		setBackground(Color.BLACK);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 330);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTcno = new JLabel("T.C NO*");
		lblTcno.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblTcno.setBackground(Color.BLACK);
		lblTcno.setBounds(23, 49, 77, 20);
		contentPane.add(lblTcno);

		txfTcno = new JTextField();
		txfTcno.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		txfTcno.setColumns(10);
		txfTcno.setBounds(23, 80, 197, 20);
		contentPane.add(txfTcno);

		JLabel lblNewLabel_1_1 = new JLabel("AD SOYAD*");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(23, 111, 86, 20);
		contentPane.add(lblNewLabel_1_1);

		TxfName = new JTextField();
		TxfName.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		TxfName.setColumns(10);
		TxfName.setBounds(23, 142, 197, 20);
		contentPane.add(TxfName);

		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre*");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(23, 175, 58, 20);
		contentPane.add(lblNewLabel_1_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(23, 206, 197, 20);
		contentPane.add(passwordField);

		JLabel lblNewLabel_1_3 = new JLabel("E-Mail");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_3.setBackground(Color.BLACK);
		lblNewLabel_1_3.setBounds(280, 49, 77, 20);
		contentPane.add(lblNewLabel_1_3);

		txfMail = new JTextField();
		txfMail.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		txfMail.setColumns(10);
		txfMail.setBounds(280, 80, 197, 20);
		contentPane.add(txfMail);

		JLabel lblNewLabel_1_4 = new JLabel("Tel.No");
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblNewLabel_1_4.setBackground(Color.BLACK);
		lblNewLabel_1_4.setBounds(280, 111, 77, 20);
		contentPane.add(lblNewLabel_1_4);

		txfTelno = new JTextField();
		txfTelno.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		txfTelno.setColumns(10);
		txfTelno.setBounds(280, 142, 197, 20);
		contentPane.add(txfTelno);

		JButton btn_Hasta_add = new JButton("Kayýt Ol");
		btn_Hasta_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txfTcno.getText().length() == 0 || TxfName.getText().length() == 0
						|| passwordField.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = patient.addPatient(txfTcno.getText(), passwordField.getText(),
								TxfName.getText(), txfMail.getText(), txfTelno.getText());
						if (control) {
							Helper.showMsg("succsess");
							LoginGui login = new LoginGui();
							login.setVisible(true);
							dispose();
						} else {
							Helper.showMsg("mistake");
						}
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
				}
			}
		});
		btn_Hasta_add.setForeground(Color.BLACK);
		btn_Hasta_add.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_Hasta_add.setBackground(new Color(95, 158, 160));
		btn_Hasta_add.setBounds(251, 198, 106, 30);
		contentPane.add(btn_Hasta_add);

		JButton btn_Back = new JButton("Geri Dön");
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui login = new LoginGui();
				login.setVisible(true);
				dispose();
			}
		});
		btn_Back.setForeground(Color.BLACK);
		btn_Back.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_Back.setBackground(Color.ORANGE);
		btn_Back.setBounds(371, 198, 106, 30);
		contentPane.add(btn_Back);

		JLabel lblNewLabel_1_5 = new JLabel("Hasta Kayýt Otomasyonu");
		lblNewLabel_1_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_5.setForeground(Color.BLACK);
		lblNewLabel_1_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1_5.setBackground(Color.GRAY);
		lblNewLabel_1_5.setBounds(146, 11, 197, 30);
		contentPane.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_1_1 = new JLabel("(*) iþaretli alanlarý doldurmak zorunludur. ");
		lblNewLabel_1_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic Light", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1_1_1.setBounds(33, 256, 417, 20);
		contentPane.add(lblNewLabel_1_1_1);
	}
}
