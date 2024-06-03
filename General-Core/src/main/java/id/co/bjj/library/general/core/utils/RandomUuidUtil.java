package id.co.bjj.library.general.core.utils;

import java.security.SecureRandom;
import java.util.UUID;

import id.co.bjj.library.general.core.constants.SecurityConstant;

public class RandomUuidUtil {
	private RandomUuidUtil() {
	}

	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString();
		SecureRandom secureRandom = new SecureRandom();
		Integer randomNumber = secureRandom.nextInt(SecurityConstant.LIMIT_RANDOM_NUMBER);
		String formattedRandom = String.format("%09d", randomNumber);
		return uuid + "-" + formattedRandom;
	}
}
