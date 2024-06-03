package id.co.bjj.library.general.core.utils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;

public class EncryptionUtil {
	private static Logger log = LogManager.getLogger(EncryptionUtil.class);

	public String encryptAES(String strToEncrypt, String key, String salt) {
		try {
			IvParameterSpec ivspec = this.getIvParameterSpec();
			SecretKeySpec secretKey = this.getSecretKeySpec(key, salt);

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.ENCRYPTION_ERROR);
		}
	}

	public String decryptAES(String strToDecrypt, String key, String salt) {
		try {
			IvParameterSpec ivSpec = this.getIvParameterSpec();
			SecretKey secretKey = this.getSecretKeySpec(key, salt);

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.DECRYPTION_ERROR);
		}

	}

	private IvParameterSpec getIvParameterSpec() {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		return new IvParameterSpec(iv);
	}

	private SecretKeySpec getSecretKeySpec(String key, String salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);

			return new SecretKeySpec(tmp.getEncoded(), "AES");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_BOOLEAN_FORMAT);
		}
	}
}
