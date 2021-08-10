package com.peg.web.util;

/**
 * 获取MES密码加密
 * @author song
 *
 */
public class EncryptionUtils {
	public static String getEncryptedPassword(String paramString) {
		return jcrypt.newCrypt("D1", paramString);
	}
}
