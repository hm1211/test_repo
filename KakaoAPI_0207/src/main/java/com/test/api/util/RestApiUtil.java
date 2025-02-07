package com.test.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import lombok.extern.log4j.Log4j;

@Log4j
public class RestApiUtil {

	// RestApi 요청
	// http연결을 위한 url, 요청 파라미터, 헤더 파라미터, 응답할 데이터 형식 전달
	public static <T> T ConnHttpGetType(String connUrl, HashMap<String, String> params,
			HashMap<String, String> headerParams, Class<T> classType) {

		try {
			// 문자열 동적 처리
			StringBuilder urlBuilder = new StringBuilder(connUrl);

			// 반복 횟수 저장 변수
			int cnt = 0;

			// https://kauth.kakao.com/oauth/token?grant_type=authorization_code&&&
			for (String key : params.keySet()) {
				if (cnt == 0) {
					// 처음오는 파라미터의 경우 앞에 ? 기호 필요
					urlBuilder.append(
							"?" + URLEncoder.encode(key, "utf-8") + "=" + URLEncoder.encode(params.get(key), "utf-8"));
				} else {
					// 두번째 파라미터 부터는 앞에 & 기호 필요
					urlBuilder.append(
							"&" + URLEncoder.encode(key, "utf-8") + "=" + URLEncoder.encode(params.get(key), "utf-8"));
				}
				cnt++;
			} // for colse

			// 실제 요청 보내기
			if (urlBuilder.toString().startsWith("https")) {
				return JsonUtil.parseJson(httpsConn(urlBuilder.toString(), headerParams), classType);
			} else {
				// http라면
				return JsonUtil.parseJson(httpConn(urlBuilder.toString(), headerParams), classType);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private static String httpConn(String connUrl, HashMap<String, String> headerData) throws IOException {
		URL url = new URL(connUrl);
		// 서비스 요청시 API에 필요한 파라미터 전달
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		for (String key : headerData.keySet()) {
			conn.setRequestProperty(key, headerData.get(key));
		}

		// 텍스트 기반의 데이터를 읽을때 사용되는 객체
		BufferedReader rd;

		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			// 응답 성공
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			// 응답 실패
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}

	private static String httpsConn(String connUrl, HashMap<String, String> headerData) throws IOException {
		URL url = new URL(connUrl);
		// 서비스 요청시 API에 필요한 파라미터 전달
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		for (String key : headerData.keySet()) {
			conn.setRequestProperty(key, headerData.get(key));
		}

		// 텍스트 기반의 데이터를 읽을때 사용되는 객체
		BufferedReader rd;

		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			// 응답 성공
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			// 응답 실패
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}
}
