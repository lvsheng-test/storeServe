package org.pack.store.utils;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtil {

	private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * AES解密
	 * 
	 * @param content 密文
	 * @param keyByte
	 * @param ivByte
	 * @return
	 */
	public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			Key sKeySpec = new SecretKeySpec(keyByte, "AES");
			// 生成iv
			AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
			params.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);// 初始化
			return cipher.doFinal(content);
		}
		catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}
}
