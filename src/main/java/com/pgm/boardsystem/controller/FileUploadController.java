package com.pgm.boardsystem.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/files/*")
public class FileUploadController {
	@GetMapping("uploadForm")
	public void fileuploadFrom() {

	}

	@PostMapping("uploadFormAction")
	public String uploadFormAction(MultipartFile[] uploads, Model model, HttpSession session) {
		String uploadFloder = session.getServletContext().getRealPath("/resources/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFloder + File.separator + today;
		List<String> mfile = new ArrayList<String>();
		log.info(saveFolder); // 실제 파일이 저장되는 경로를 출력

		for (MultipartFile multipartFile : uploads) {
			String orifile = multipartFile.getOriginalFilename(); // 원본 파일이름
			UUID uuid = UUID.randomUUID(); // 랜덤한 UUID를 생성하여 이름의 중복을 없앤다.
			String uploadfileName = uuid.toString() + "_" + orifile; // 업로드되는 파일의 이름을 설정
			log.info("-----------------------------------");
			log.info("Origin file name:" + multipartFile.getOriginalFilename()); // 업로드되는 원본 파일이름을 출력.
			log.info("Upload file name:" + uploadfileName); // 업로드되는 파일의 이름을 출력.
			File saveFile = new File(saveFolder, uploadfileName); // 파일을 저장한다.
			try {
				multipartFile.transferTo(saveFile);
				mfile.add(orifile); // 배열에 원본 파일이름을 추가.
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("uploadFiles", mfile);
		return "files/fileResult";

	}
}
