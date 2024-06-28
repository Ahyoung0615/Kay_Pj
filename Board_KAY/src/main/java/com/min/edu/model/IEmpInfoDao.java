package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.EmpInfoDto;

public interface IEmpInfoDao {
	
	// 로그인
	public EmpInfoDto login(Map<String, Object> map);
	
	// 아이디 중복 검사
	public EmpInfoDto idCheck(String id);
	
	// 회원 가입
	public boolean singIn(EmpInfoDto dto);
	
	// 회원 승인
	public boolean memberSuccess(List<String> list);
	
	// 회원 삭제
	public boolean memberDel(List<String> list);
	
	// 회원 목록 조회
	public List<EmpInfoDto> selectMemberInfo(Map<String, Object> map);
	
	// 전체 회원 수
	public int countMemberInfo();
	
	// 대기 회원 조회
	public List<EmpInfoDto> waitingMember(Map<String, Object> map);
	
	// 대기 회원 수
	public int countWaiting();
}
