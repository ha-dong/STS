package com.example.storycraft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // /jsp/editInquiry 경로는 여기서만 사용되도록 설정
	@GetMapping("/jsp/editInquiry")
	public String showEditInquiryPage() {
	    return "/jsp/editInquiry";
	}

}

