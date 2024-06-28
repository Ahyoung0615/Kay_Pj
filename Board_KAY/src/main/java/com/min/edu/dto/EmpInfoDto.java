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
public class EmpInfoDto {

	private String id;
	private String password;
	private String name;
	private String phone;
	private String enable;
	private String auth;
	private String filename;
}
