package org.pack.store.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 解密
 */
public class WXBizDataCrypt {

	private static final Logger logger = LoggerFactory.getLogger(WXBizDataCrypt.class);

	public static String illegalAesKey = "-41001";// 非法密钥

	public static String illegalIv = "-41002";// 非法初始向量

	public static String illegalBuffer = "-41003";// 非法密文

	public static String decodeBase64Error = "-41004"; // 解码错误

	public static String noData = "-41005"; // 数据不正确

	/**
	 * 检验数据的真实性，并且获取解密后的明文.
	 * 
	 * @param encryptedData string 加密的用户数据
	 * @param iv string 与用户数据一同返回的初始向量
	 * @return data string 解密后的原文
	 * @return String 返回用户信息
	 */
	public static String decryptData(String sessionKey, String appid, String encryptedData, String iv) {
		if (StringUtils.length(sessionKey) != 24) {
			logger.error("WXBizDataCrypt error: {}", illegalAesKey);
			return null;
		}
		// 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
		byte[] aesKey = Base64.decodeBase64(sessionKey);

		if (StringUtils.length(iv) != 24) {
			logger.error("WXBizDataCrypt error: {}", illegalIv);
			return null;
		}
		// 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
		byte[] aesIV = Base64.decodeBase64(iv);

		// 对称解密的目标密文为 Base64_Decode(encryptedData)
		byte[] aesCipher = Base64.decodeBase64(encryptedData);

		try {
			byte[] resultByte = AESUtil.decrypt(aesCipher, aesKey, aesIV);
			if (null != resultByte && resultByte.length > 0) {
				String userInfo = new String(resultByte, "UTF-8");
				JSONObject jsons = JSON.parseObject(userInfo);
				String id = jsons.getJSONObject("watermark").getString("appid");
				if (!StringUtils.equals(id, appid)) {
					logger.error("WXBizDataCrypt error: {}", illegalBuffer);
					return null;
				}
				return userInfo;
			}
			else {
				logger.error("WXBizDataCrypt error: {}", noData);
				return null;
			}
		}
		catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	public static String decrypt(String openId, String sessionKey, String encryptedData, String iv) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(Base64Utils.decodeFromString(sessionKey), "AES");
		String userInfo = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(Base64Utils.decodeFromString(iv)));
			byte[] decryptBytes = cipher.doFinal(Base64Utils.decodeFromString(encryptedData));
			userInfo = new String(decryptBytes, "UTF-8");
		}
		catch (Exception e) {
			logger.error("用户[{}]微信数据解密失败[{}]", openId, e.getMessage());
		}
		return userInfo;
	}

	/**
	 * 手机号解密
	 */
	public static String decryptData(String encryptData, String sessionKey, String iv) {
		if (StringUtils.length(sessionKey) != 24) {
			logger.error("WXBizDataCrypt error: {}", illegalAesKey);
			return null;
		}
		// 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
		byte[] aesKey = Base64.decodeBase64(sessionKey);

		if (StringUtils.length(iv) != 24) {
			logger.error("WXBizDataCrypt error: {}", illegalIv);
			return null;
		}
		// 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
		byte[] aesIV = Base64.decodeBase64(iv);

		// 对称解密的目标密文为 Base64_Decode(encryptedData)
		byte[] aesCipher = Base64.decodeBase64(encryptData);

		try {
			byte[] resultByte = AESUtil.decrypt(aesCipher, aesKey, aesIV);
			if (null != resultByte && resultByte.length > 0) {
				String userInfo = new String(resultByte, "UTF-8");
				return userInfo;
			}
			else {
				logger.error("WXBizDataCrypt error: {}", noData);
				return null;
			}
		}
		catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}



}
