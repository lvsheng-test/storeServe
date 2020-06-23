package org.pack.store.utils.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	public static String toJSONString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T parseObject(String text, Class<T> clazz) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(text, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
