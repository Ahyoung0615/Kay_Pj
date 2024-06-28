package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.EduBoardDto;

public interface IEduBoardDao {
	
	// 전체 조회
	public List<EduBoardDto> allSelect(Map<String, Object> map);
	
	// 전체 글 갯수
	public int countBoard();
	
	// 내 글 조회
	public List<EduBoardDto> selectMyPost(Map<String, Object> map);
	
	// 내 글 갯수
	public int countMyPost(String id);
	
	// 상세 조회
	public EduBoardDto detailSelect(String seq);
	
	// 입력
	public boolean insertBoard(Map<String, Object> map);
	
	// 수정
	public boolean updateBoard(Map<String, Object> map);
	
	// 삭제
	public boolean deleteBoard(String seq);
	
	// 다중 삭제
	public boolean multiDeleteBoard(List<String> list);
}
