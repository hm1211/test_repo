package com.test.api.util;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Log4j
public class JsonUtil {

	// String을 Json으로 변환 기능
	public static <T> T parseJson(String jsonString, Class<T> clazz) {
		
		Gson gson = new Gson();
		T result = gson.fromJson(jsonString, clazz);
		log.info("Gson: " + jsonString);
		log.info("Gson: " + result);
		
		return result;
	}
}
