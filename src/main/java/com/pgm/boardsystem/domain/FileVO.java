package com.pgm.boardsystem.domain;

import lombok.Data;

@Data
public class FileVO {
	private int fno;
	private int bno;
	private String savefolder;
	private String savefile;
	private String originfile;
	private String filetype;
}
