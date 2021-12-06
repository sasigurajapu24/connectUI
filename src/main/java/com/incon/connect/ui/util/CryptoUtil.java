package com.incon.connect.ui.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class CryptoUtil {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 't', 'r', 'U', 'E', 'C', 'H', 'k', 'B', '0', 'g', 'a', 'v', 'a',
			'l', 'l', '1' };

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new sun.misc.BASE64Encoder().encode(encVal);
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new sun.misc.BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
	
	public static void main(String[] args) throws Exception {
		String s = "lki2YtnZVZP3obcsrowSIvKE8EZw0XHT2u7QN8/yWMVqwo5MR1LzajpRInb992y2Kg9CP+zg73wJ\n"+
"XzLGSMr3zXszUeuA5SsFq37esansDmKarJpGxYnZGuwAD32UkMn78xkLynAvNK5Vaj1s3zFNYx85\n"+ 
"XULkk3t/YrZURIrd8E8=";
		
		System.out.println(decrypt(s));
	}
}
