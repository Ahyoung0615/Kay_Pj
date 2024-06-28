package com.min.edu.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EduBoardDto {

	private int seq;
	private String id;
	private String title;
	private String content;
	private String delflag;
	private String createat;
	
	private String name;
}
