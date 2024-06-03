package id.co.bjj.library.general.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegerCheckerUtil {
	private static Logger log = LogManager.getLogger(IntegerCheckerUtil.class);

	private IntegerCheckerUtil() {
	}

	public static Boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
			return Boolean.TRUE;
		} catch (NumberFormatException ex) {
			log.error(ex.getMessage(), ex);
			return Boolean.FALSE;
		}
	}
}
