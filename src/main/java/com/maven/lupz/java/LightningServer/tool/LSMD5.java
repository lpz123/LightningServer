package com.maven.lupz.java.LightningServer.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LSMD5 {
	
	/**
	 * 把String 计算成md5值
	 * @param typeCode
	 * @param message
	 * @return
	 */
	public static String Encode(HashCodeType typeCode, String message) {
		return Encode(typeCode, message.getBytes());
	}
	
	private static String Encode(HashCodeType typeCode, byte[] message) {
		MessageDigest md;
		String encode = null;
		try {
			md = MessageDigest.getInstance(typeCode.getValue());
			encode = bytesToHexString(md.digest(message));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encode;
	}
	
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return "";
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public enum HashCodeType {
		MD5("MD5"), SHA("SHA"), SHA256("SHA-256"), SHA512("SHA-512");

		private final String _value;

		HashCodeType(String value) {
			this._value = value;
		}

		public String getValue() {
			if (_value == null)
				return "";
			return _value;
		}
	}
	
}
