package com.pgm.boardsystem.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pgm.boardsystem.domain.BoardVO;
import com.pgm.boardsystem.domain.FileVO;
import com.pgm.boardsystem.domain.PageVO;
import com.pgm.boardsystem.service.BoardService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/board/")
public class BoardController {
	@Autowired
	BoardService boardService;

	@GetMapping("register")
	public void register() {

	}

//	@PostMapping("insert")
//	public String registr(BoardVO vo) {
//		boardService.insert(vo);
//		return "redirect:/board/list";
//	}

	@PostMapping("insert")
	// MultipartFile[] uploads에서 uploads는 jsp페이지의 input태그의 아이디와 같아야한다.
	public String register(BoardVO board, MultipartFile[] uploads, HttpSession session, HttpServletRequest req) {
		// 로그 정보 출력
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + board.getBno() + "글을 작성.");

		String uploadFloder = session.getServletContext().getRealPath("/resources/upload");
		log.info("uploads.length:" + uploads.length);
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFloder + File.separator + today; // 업로드될 경로를 설정
		System.out.println(saveFolder); // 업로드되는 경로를 출력
		File folder = new File(saveFolder); // 해당 경로를 생성

		// 폴더가 존재하지 않을 경우
		if (!folder.exists()) {
			folder.mkdirs(); // 폴더를 생성
		}
		List<FileVO> fileList = new ArrayList<FileVO>();

		// uploads로 부터 업로드가 된 여러 개의 파일을 하나씩 가져온다.
		for (MultipartFile multipartFile : uploads) {
			String originFile = multipartFile.getOriginalFilename();

			// 파일을 업로드 했을 경우
			if (!originFile.isEmpty()) {
				// 로그 정보 출력
				log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + board.getBno() + "번의 글에 파일을 업로드.");
				FileVO fileVO = new FileVO();
				UUID uuid = UUID.randomUUID(); // 랜덤한 UUID 생성
				String saveFileName = uuid.toString() + "_" + originFile; // 서버에 저장할 파일 이름을 설정
				String fileType = multipartFile.getContentType(); // 파일의 유형
				log.info("fileType:" + fileType); // 파일의 유형을 출력
				fileType = fileType.substring(0, fileType.indexOf("/"));
				fileVO.setOriginfile(originFile); // 원본 파일 이름 설정
				fileVO.setFiletype(fileType); // 파일 유형을 설정
				fileVO.setSavefile(saveFileName); // 저장할 파일 이름으로 설정
				fileVO.setSavefolder(today); // 저장할 파일 경로를 설정

				try {
					File savefile = new File(saveFolder, saveFileName);
					multipartFile.transferTo(savefile);
					fileList.add(fileVO);
				} catch (IllegalStateException | IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} else {
				String filename = uploads[0].getOriginalFilename();
				if (filename == null) {
					BoardVO vo1 = boardService.read(board.getBno());
					// System.out.println(board.getBno());
					board.setFileList(vo1.getFileList());
				}

			}
		}
		board.setFileList(fileList);
		boardService.insert(board);

		return "redirect:/board/list";
	}

	@GetMapping("update/{bno}")
	public String update(@PathVariable("bno") int bno, Model model, HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + bno + "번의 글을 수정하는 중.");
		model.addAttribute("board", boardService.read(bno));
		return "board/update";
	}

	@PostMapping("update")
	public String update(BoardVO vo, MultipartFile[] uploads, HttpServletRequest req) {
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + vo.getBno() + "번의 글을 수정.");
		boardService.update(vo);
		return "redirect:/board/list";
	}

	@GetMapping("delete/{bno}")
	public String delete(@PathVariable("bno") int bno, HttpServletRequest req) {
		String strReferer = req.getHeader("referer"); //이전 URL 가져오기
		// 직접 접근 막기
		if (strReferer == null) {
			log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + bno + "번의 글삭제를 잘못된 경로로 접속.");
			return "redirect:/error";
		}
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + bno + "번의 글을 삭제.");
		boardService.delete(bno);
		return "redirect:/board/list";
	}

	@GetMapping("list")
	public String list(Model model, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word) {

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 Community에 접속.");
		if (!(pageNum == null)) {
			log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + pageNum + "번의 페이지를 읽음.");
		}
		int pageSize = 5; // 한 블럭에 보일 게시글 수
		
		// 게시글 검색
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<BoardVO> list = boardService.findAll(hm);
		log.info(list.toString());
		int count = boardService.boardCount(hm);
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/list";
	}

	// @GetMapping("list")
	public String list(Model model, String pageNum) {
		log.info("listController");

		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 3;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("pageStart", (currentPage - 1) * pageSize);
		hm.put("pageSize", pageSize);

		List<BoardVO> list = boardService.findPage(hm);
		System.out.println(list.get(0));
		int count = boardService.boardCount();
		PageVO page = new PageVO(count, currentPage, pageSize);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		return "board/list";
	}

	// @GetMapping("list")
	public String list(Model model) {
		List<BoardVO> list = boardService.getList();
		model.addAttribute("list", list);
		model.addAttribute("boardCount", boardService.boardCount());
		return "board/list";
	}

	// @GetMapping("detail")
	// public String read(@RequestParam("bno") int bno,Model model) {
	@GetMapping("detail")
	public String read(@RequestParam("bno") int bno, String pageNum, HttpServletRequest req,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word, Model model) {
		// 로그 정보 출력
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + bno + "번의 글을 읽음.");

		model.addAttribute("board", boardService.read(bno));
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("field", field);
		model.addAttribute("word", word);
		return "board/detail";
	}

	@GetMapping("download/{fno}")
	public String download(@PathVariable("fno") int fno, HttpSession session, HttpServletRequest req,
			HttpServletResponse res) {
		// 로그 정보 출력
		log.info(HomeController.etRemoteAddr(req) + "의 아이피에서 " + fno + "번의 파일을 다운로드.");

		FileVO fileVO = boardService.getFile(fno);
		log.info("다운로드 된 파일:" + fileVO);
		String fileName = null;

		try {
			// 파일이 저장되어있는 경로
			String path = session.getServletContext().getRealPath("/resources/upload/" + fileVO.getSavefolder());
			File file = new File(path, fileVO.getSavefile()); // 파일을 가져온다.
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file)); // 가져온 파일을 버퍼에 저장한다.
			String header = req.getHeader("User-Agent");
			if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
				// 인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩 방식
				fileName = URLEncoder.encode(fileVO.getOriginfile(), "UTF-8");

			} else {
				// 그 외 나머지 브라우저인 경우
				fileName = new String(fileVO.getOriginfile().getBytes("UTF-8"), "iso-8859-1");
			}

			res.setContentType("application/octet-stream");
			// 다운로드와 다운로드되는 파일의 이름
			res.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			// 파일을 복사
			FileCopyUtils.copy(in, res.getOutputStream());
			in.close();
			res.getOutputStream().flush(); // 남은 내용을 지운다.
			res.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		return null;

	}

}
