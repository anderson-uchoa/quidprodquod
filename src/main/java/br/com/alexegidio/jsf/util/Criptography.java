package br.com.alexegidio.jsf.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Criptography {

	public static String encryptString(String phrase) {
		return DigestUtils.md5Hex(phrase);
	}
}
