package com.edu.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.model.EduBoardDaoImpl;
import com.min.edu.model.IEduBoardDao;

public class EduBoard_JUnitTest {

//	@Test
	public void test() {
		IEduBoardDao dao = new EduBoardDaoImpl();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", "1");
		map.put("last", "5");
		
		List<EduBoardDto> list = dao.allSelect(map);
		System.out.println(list);
		assertNotEquals(0, list.size());
	}

//	@Test
	public void eduBoard_JUnitTest() {
		IEduBoardDao dao = new EduBoardDaoImpl();
		EduBoardDto dto = new EduBoardDto();
		dto.setSeq(1);
		// 상세 조회
		EduBoardDto detailDto = dao.detailSelect(String.valueOf(dto.getSeq()));
		assertNotNull(detailDto);
		
		// 입력
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("id", detailDto.getId());
		insertMap.put("title", "Insert_JUnitTest");
		insertMap.put("content", "Insert_JUnitTest");
		
		boolean insertIsc = dao.insertBoard(insertMap);
		assertTrue(insertIsc);
		// 수정
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("content", "update_JUnitTest");
		updateMap.put("seq", String.valueOf(dto.getSeq()));
		
		boolean updateIsc = dao.updateBoard(updateMap);
		assertTrue(updateIsc);
		
		// 삭제
		boolean deleteIsc = dao.deleteBoard(String.valueOf(dto.getSeq()));
		assertTrue(deleteIsc);
		
		// 다중 삭제
		String[] seqList = {"1","2","3"};
		boolean multidelIsc = dao.multiDeleteBoard(Arrays.asList(seqList));
		assertTrue(multidelIsc);
	}
	
	@Test
	public void selectMtPost_JUnitTest() {
		// 내 글 갯수 확인
		IEduBoardDao dao = new EduBoardDaoImpl();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", "1");
		map.put("last", "5");
		map.put("id", "HAPPY");
		
		List<EduBoardDto> list = dao.selectMyPost(map);
		assertNotEquals(0, list);
		// 내 글 확인
		int cnt = dao.countMyPost("HAPPY");
		assertNotNull(cnt);
	}
}
