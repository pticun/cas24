package org.arch.core.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cryptographic {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private Cipher ecipher;
	private Cipher dcipher;

	public Cryptographic(String secretKey) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
		try {
			ecipher = Cipher.getInstance("AES");
			dcipher = Cipher.getInstance("AES");
			ecipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			dcipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		} catch (Exception e) {
			log.debug(ExceptionUtils.getStackTrace(e));
		}
	}

	public String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes("UTF-8");
			byte[] enc = ecipher.doFinal(utf8);

			return  Base64.encodeBase64String(enc);
		} catch (Exception e) {
			log.debug(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public String decrypt(String str) {
		try {
			byte[] dec = Base64.decodeBase64(str);

			byte[] utf8 = dcipher.doFinal(dec);

			return new String(utf8, "UTF-8");
		} catch (Exception e) {
			log.debug(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
}
