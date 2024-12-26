package com.pgm.boardsystem.controller;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/ban/*")
public class BanController {

	@GetMapping("memberlistban")
	public String memberlistban(HttpServletRequest request) {
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 memberlist를 잘못된 경로로 접속.");
		return "redirect:/error";
	}

	@GetMapping("modifyban/{id}")
	public String modifyban(@PathVariable("id") String id, HttpServletRequest request) {
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 " + id + "계정의 회원수정을 잘못된 경로로 접속.");
		return "redirect:/error";
	}

	@GetMapping("updateban/{bno}")
	public String updateban(@PathVariable("bno") int bno, HttpServletRequest request) {
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 " + bno + "번의 글수정을 잘못된 경로로 접속.");
		return "redirect:/error";
	}

	@GetMapping("detailban/{bno}")
	public String deleteban(@PathVariable("bno") int bno, HttpServletRequest request) {
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 " + bno + "번의 글보기를 잘못된 경로로 접속.");
		return "redirect:/error";
	}
	
	@GetMapping("insertban/{bno}")
	public String insertban(@PathVariable("bno") int bno, HttpServletRequest request) {
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 글쓰기를 잘못된 경로로 접속.");
		return "redirect:/error";
	}
}
