package id.co.bjj.library.general.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashUtil {
	private Md5HashUtil() {
	}

	public static byte[] digest(byte[] input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md.digest(input);
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
