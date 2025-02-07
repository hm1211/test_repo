package com.test.api.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.test.api.model.KakaoAuthResponse;
import com.test.api.model.KakaoTokenResponse;
import com.test.api.util.RestApiUtil;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class KakaoLoginService {

	// 토큰 발급 기능 구현
	public KakaoTokenResponse getToken(KakaoAuthResponse response) {
		String url = "https://kauth.kakao.com/oauth/token";

		// 토큰 발급 요청을 위한 본문 파라미터 저장
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("grant_type", "authorization_code");
		params.put("client_id", "3d78a9f235b0ea1c45d65b98fe4deb52");
		params.put("redirect_uri", "http://localhost:8090/authResult");
		params.put("code", response.getCode());

		// 토큰 발급 요청을 위한 헤더 파라미터 저장
		HashMap<String, String> headerparams = new HashMap<String, String>();
		headerparams.put("Content-Type", "Content-Type: application/x-www-form-urlencoded;charset=utf-8");

		KakaoTokenResponse tokenResult = null;

		tokenResult = RestApiUtil.ConnHttpGetType(url, params, headerparams, KakaoTokenResponse.class);
		if (tokenResult == null) {
			log.info("카카오 토큰 : null");
		} else {
			log.info("카카오 토큰 : " + tokenResult.toString());
		}
		return tokenResult;
	}
}
