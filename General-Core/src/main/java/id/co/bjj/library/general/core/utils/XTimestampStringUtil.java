package id.co.bjj.library.general.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XTimestampStringUtil {
	private XTimestampStringUtil() {
	}

	private static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ssXXX";

	public static String convertDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);
		return simpleDateFormat.format(date);
	}
}
