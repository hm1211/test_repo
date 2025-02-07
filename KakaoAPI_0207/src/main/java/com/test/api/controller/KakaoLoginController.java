package com.test.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.api.model.KakaoAuthResponse;
import com.test.api.model.KakaoTokenResponse;
import com.test.api.service.KakaoLoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
public class KakaoLoginController {
	
	private final KakaoLoginService kakaoLoginService;

	@GetMapping("/login")
	public void loginView() {

	}

	@GetMapping("/authResult")
	public String authResult(KakaoAuthResponse response, Model model) {
		log.info("인가 코드: " + response);

		// 인가코드를 이용하여 토큰 발급
		KakaoTokenResponse tokenResponse = kakaoLoginService.getToken(response);
		
		if(tokenResponse != null && tokenResponse.getAccess_token() != null) {
			// 토큰이 정상적으로 받아진 경우
			model.addAttribute("loginStatus", true);
		}else {
			model.addAttribute("loginStatus", false);
		}
		
		return "login";
	}
}
