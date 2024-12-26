package com.pgm.boardsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgm.boardsystem.domain.SampleVO;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/sample/*")
@Log
public class SampleController {

	@GetMapping(value = "check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(1, "aaa", "bbb");
		ResponseEntity<SampleVO> result = null;
		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}

	@GetMapping("getSampleMap")
	public Map<String, SampleVO> getSampleMap() {
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("first", new SampleVO(1, "GilDong", "Hone"));
		map.put("second", new SampleVO(1, "GilDong", "kim"));
		return map;
	}

	@GetMapping("getSample")
	public SampleVO getSample() {
		log.info("getSample");
		return new SampleVO(1, "gildong", "hong");
	}

	public List<SampleVO> getList() {
		List<SampleVO> list = new ArrayList<SampleVO>();
		for (int i = 0; i < 10; i++) {
			list.add(new SampleVO(i, "first_" + i, "list_" + i));
		}
		return list;
	}
}
