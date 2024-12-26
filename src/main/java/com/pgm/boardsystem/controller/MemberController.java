package com.pgm.boardsystem.controller;

import java.util.HashMap; 
import java.util.List;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgm.boardsystem.domain.MemberVO;
import com.pgm.boardsystem.domain.PageVO;
import com.pgm.boardsystem.service.MemberService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("memberlist")
	public String memberlist(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word) {
		// model.addAttribute("memberlist", memberService.getList());

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 MemberList에 접속.");
		// 페이지 수가 null인 경우를 예외처리
		if (!(pageNum == null)) {
			log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 MemberList " + pageNum + "번의 페이지를 읽음.");
		}
		int pageSize = 5; // 한 블럭에 보일 맴버 수

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<MemberVO> list = memberService.findAll(hm);
		int membercount = memberService.memberCount(hm);
		PageVO page = new PageVO(membercount, currentPage, pageSize);

		model.addAttribute("memberlist", list);
		model.addAttribute("count", membercount);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);

		return "member/memberlist";

	}

	@GetMapping("join")
	public void join(HttpServletRequest request) {
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 Join에 접속.");
	}

	public String idCheck(String id) {
		System.out.println("idChecking");
		int cnt = memberService.idCheck(id);
		System.out.println("cnt" + cnt);
		if (cnt != 0) {
			return "true";
		} else {
			return "false";
		}

	}

	// 자바스크립트의 ajax가 전송한 회원가입 정보들을 해당 메소드가 받음
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody MemberVO member, HttpServletRequest request) {

		// member의 getId() 메소드로 아이디 값을 받아서 memberService의 idCheck() 메소드로 해당 아이디를 검색한다.
		// 입력된 아이디가 중복된 값인지를 확인
		int cnt = memberService.idCheck(member.getId());
		// cnt의 값이 ture이면 1의 값을 가지므로 아래의 if문에 해당함.
		if (cnt != 0) {
			return "fail";
		}
		memberService.join(member);
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 " + member.getId() + "계정으로 회원가입.");
		return "success";
	}

	@GetMapping("login")
	public void login(HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 Login에 접속.");
	}

	// 자바스크립트에서 ajax가 전송한 회원가입 정보들을 해당 메소드가 받는다.
	@PostMapping("login")
	@ResponseBody
	public String login(String id, String pass, HttpSession session, HttpServletRequest req) {

		MemberVO member = memberService.loginCheck(id);
		String result = "no"; // 회원아닐때
		// member가 null이 아니면. 입력된 id가 있고 sql구문을 통해서 검색된 member의 값이 있을 경우.
		if (member != null) {
			// 입력된 비밀번호가 저장된 비밀번호와 일치할 경우.
			if (member.getPass().equals(pass)) {
				// 로그인 정보 출력
				log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 아이디:" + member.getId() + ",비밀번호:" + pass
						+ "의 계정으로 로그인.");
				// 새로운 세션을 생성한다. 세션은 로그인이 유지되는 것을 말한다.
				session.setAttribute("sMember", member);
				result = "success";
			} else {
				result = "passFail";
			}
		}
		return result;
	}

	@GetMapping("modify/{id}")
	public String modify(@PathVariable("id") String id, Model model, HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + "계정을 수정하는 중.");
		model.addAttribute("member", memberService.read(id));
		return "member/modify";
	}

	@PostMapping("modify")
	@ResponseBody
	public String modify(@RequestBody MemberVO member, HttpServletRequest request) {
		memberService.modify(member);
		log.info(HomeController.etRemoteAddr(request) + "의 아이피에서 " + member.getId() + " 계정을 수정.");
		return "success";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") String id, Model model, HttpSession session, HttpServletRequest req) {
		String strReferer = req.getHeader("referer"); //이전 URL 가져오기
		// 직접 접근 막기
		if (strReferer == null) {
			log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + "계정 탈퇴를 잘못된 경로로 접속.");
			return "redirect:/error";
		}
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + "계정을 탈퇴.");
		session.invalidate();
		memberService.delete(id);
		return "redirect:/index";
	}

	// 회원추방
	@GetMapping("ban/{id}")
	public String ban(@PathVariable("id") String id, Model model, HttpSession session, HttpServletRequest req) {
		String strReferer = req.getHeader("referer"); //이전 URL 가져오기
		// 직접 접근 막기
		if (strReferer == null) {
			log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + "계정 추방을 잘못된 경로로 접속.");
			return "redirect:/error";
		}
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + id + " 계정이 추방.");
		memberService.delete(id);
		return "redirect:/member/memberlist";
	}

	// 로그아웃 메소드
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 로그아웃 됨.");
		session.invalidate();
		// 제자리 걸음.
		return "redirect:/index";
	}

}
