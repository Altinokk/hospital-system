package Helper;

import javax.swing.JOptionPane;

public class Helper {

	public static void showMsg(String str) {
		String msg;
		switch (str) {
		case "fill":
			msg = "Lütfen Boþ Alanlarý Doldurunuz";
			break;
		case "succsess":
			msg = "Kayýt Baþarýlý.";
			break;
		case "mistake":
			msg = "Bir Hata OLustu";
			break;
		default:
			msg = str;

		}

		JOptionPane.showMessageDialog(null, msg, "Uyarý", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean askmsg(String ptr) {
		String msgg;
		switch (ptr) {
		case "sure":
			msgg = "Bu islemi yapmak istediginizden emin misiniz ?";
			break;
		default:
			msgg = ptr;
			break;
		}
		int res = JOptionPane.showConfirmDialog(null, msgg, "Uyari", JOptionPane.YES_NO_OPTION);
		if (res == 0)
			return true;
		else
			return false;
	}

}
