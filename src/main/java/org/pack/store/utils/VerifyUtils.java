package org.pack.store.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.pack.store.exception.ResultRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 校验接口
 * 
 * @author Season Shen
 * @since cloudsWechat1.3.0
 */
public class VerifyUtils {

	private static final Logger logger = LoggerFactory.getLogger(VerifyUtils.class);

	/**
	 * 参数校验
	 * 
	 * @param jsonObject
	 * @param params
	 */
	public static void notBlanks(JSONObject jsonObject, String... params) {
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				String param = params[i];
				String str = jsonObject.getString(param);
				if (StringUtils.isBlank(str)) {
					logger.error("参数:" + param + " 此参数为空");
					throw new ResultRuntimeException("参数有误!");
				}
			}
		}
	}
	
	public static boolean isNotBlanks(JSONObject jsonObject, String... params) {
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				String param = params[i];
				String str = jsonObject.getString(param);
				if (StringUtils.isBlank(str)) {
					logger.error("参数:" + param + " 此参数为空");
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断类里每个属性是否不为空
	 * 
	 * @param o
	 * @return
	 * @author zmj
	 */
	public static boolean isNotBlank(Object o) {
		if (null == o) {
			return false;
		}
		Class<?> clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			try {
				f.setAccessible(true);
				if (null == f.get(o) || "" == f.get(o)) {
					return false;
				}
			}
			catch (IllegalAccessException e) {
				logger.error("反射获取判断参数有误", e);
				throw new ResultRuntimeException("参数有误!");
			}
		}
		return true;
	}

	/**
	 * 判断类里指定属性是否不为空
	 * 
	 * @param o
	 * @return
	 * @author zmj
	 */
	public static boolean isNotBlank(Object o, String... params) {
		if (null == o) {
			return false;
		}
		Class<?> clazz = o.getClass();
		for (String s : params) {
			Field field = null;
			try {
				field = clazz.getDeclaredField(s);
				field.setAccessible(true);
				if (null == field.get(o) || "" == field.get(o)) {
					return false;
				}
			}
			catch (Exception e) {
				logger.error("反射获取判断参数有误", e);
				throw new ResultRuntimeException("参数有误!");
			}
		}
		return true;
	}
	
}
