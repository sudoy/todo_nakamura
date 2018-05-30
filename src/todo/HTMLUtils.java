package todo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLUtils {

	public static String stars(int priority) {

		if(priority == 3) {
			return "★★★";
		} else if(priority == 2) {
			return "★★";
		} else {
			return "★";
		}

	}

	public static String formating(Date limit_day) {
		// nullの場合、空白を返すようにする
		if(limit_day != null) {
			String str = new SimpleDateFormat("yyyy/MM/dd").format(limit_day);
			return str;
		} else {
			return "";
		}

	}

}
