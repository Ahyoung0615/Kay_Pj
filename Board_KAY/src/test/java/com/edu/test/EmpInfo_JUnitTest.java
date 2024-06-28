package com.edu.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoDaoImpl;
import com.min.edu.model.IEmpInfoDao;

public class EmpInfo_JUnitTest {

//	@Test
	public void empInfo_Login_JUnitTest() {
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		
		EmpInfoDto idChkDto = dao.idCheck("QQQ");
		assertNull(idChkDto);
		
//		EmpInfoDto dto = new EmpInfoDto();
//		dto.setId("new Test");
//		dto.setPassword("new Test");
//		dto.setName("new Test");
//		boolean isc = dao.singIn(dto);	
//		assertTrue(isc);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", "JUnitTest6");
//		map.put("password", "JUnitTest");
//		EmpInfoDto loginDto =  dao.login(map);
//		assertNotNull(loginDto);
	}

//	@Test
	public void selectMemberInfo_JUnitTest() {
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", "1");
		map.put("last", "5");
		
		List<EmpInfoDto> list = dao.selectMemberInfo(map);
		assertNotEquals(0, list.size());
		System.out.println(list);
	}
	
//	@Test
	public void memberDel_Success_JUnitTest() {
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		
		String[] ids = {"newUser", "USER", "FILETEST"};
		boolean successIsc = dao.memberSuccess(Arrays.asList(ids));
		assertTrue(successIsc);
		
		boolean delIsc = dao.memberDel(Arrays.asList(ids));
		assertTrue(delIsc);
	}
	
	@Test
	public void waitingMember_JUnitTest() {
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", "1");
		map.put("last", "5");
		
		List<EmpInfoDto> list = dao.waitingMember(map);
		assertNotNull(list);
	}
	
}
