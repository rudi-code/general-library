package id.co.bjj.library.general.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogIdGeneratorUtil {
	private static Logger log = LogManager.getLogger(LogIdGeneratorUtil.class);

	private LogIdGeneratorUtil() {
	}

	public static String generateLogId(String requestId, String sourceSystem) {
		log.info("Log Id Generator Util, Generate Log Id");

		SimpleDateFormat simpleDateFormatTimeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String randomUuid = UUID.randomUUID().toString();
		Date dateNow = new Date();

		String logId = requestId + "_" + sourceSystem + "_" + randomUuid + "_"
				+ simpleDateFormatTimeStamp.format(dateNow);
		byte[] digestLogId = Md5HashUtil.digest(logId.getBytes());

		return Md5HashUtil.bytesToHex(digestLogId);
	}
}
