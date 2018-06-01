package todo.utils;

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

	public static String check(String param, String value) {
		if(param == null && value.equals("3")) {
			return "checked";
		} else if (param.equals(value)) {
			return "checked";
		} else {
			return "";
		}
	}

	public static String formating(Date limitDay) {
		// nullの場合、空白を返すようにする
		if(limitDay != null) {
			String str = new SimpleDateFormat("yyyy/MM/dd").format(limitDay);
			return str;
		} else {
			return "";
		}

	}

}
