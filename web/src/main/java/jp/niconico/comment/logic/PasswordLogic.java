package jp.niconico.comment.logic;

import java.security.MessageDigest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.seasar.framework.util.MessageDigestUtil;

public class PasswordLogic {

	public static boolean isCorrectPassword(String userPass, String inputPass, String salt) {
		return StringUtils.equals(userPass, streching(inputPass, salt));
	}

	public static String generateSalt() {
		return RandomStringUtils.randomAscii(10);
	}

	public static String streching(String inputPass, String salt) {
		return getStringDigest(salt + inputPass);
	}

	/**
	 * 暗号化した文字列を返します
	 * 
	 * @param str
	 * @return
	 */
	protected static String getStringDigest(String str) {
		StringBuilder hex = new StringBuilder();
		MessageDigest md = MessageDigestUtil.getInstance("MD5");
		md.update(str.getBytes());
		byte[] digest = md.digest();
		for (int i = 0; i < digest.length; i++) {
			if ((0xFF & digest[i]) < 16) {
				hex.append("0");
			}
			hex.append(Integer.toHexString(0xFF & digest[i]));
		}
		return hex.toString();
	}
}
