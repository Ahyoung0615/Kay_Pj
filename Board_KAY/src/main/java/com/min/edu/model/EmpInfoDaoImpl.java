package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.EmpInfoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmpInfoDaoImpl implements IEmpInfoDao {

	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.EmpInfoDaoImpl.";
	
	// 로그인
	@Override
	public EmpInfoDto login(Map<String, Object> map) {
		log.info("EmpInfoDto login {}", map);
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "login", map);
	}
	
	// 아이디 중복 체크
	@Override
	public EmpInfoDto idCheck(String id) {
		log.info("EmpInfoDto idCheck {}", id);
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "idCheck", id);
	}

	// 회원가입
	@Override
	public boolean singIn(EmpInfoDto dto) {
		log.info("EmpInfoDto singIn {}", dto);
		SqlSession session = factory.openSession(true);
		return (session.insert(NS + "singIn", dto) == 1) ? true : false;
	}

	// 회원 가입 승인
	@Override
	public boolean memberSuccess(List<String> list) {
		log.info("EmpInfoDto memberSuccess {}", list);
		SqlSession session = factory.openSession(true);
		return (session.update(NS + "memberSuccess", list) >= 1) ? true : false;
	}

	// 회원 목록 조회
	@Override
	public List<EmpInfoDto> selectMemberInfo(Map<String, Object> map) {
		log.info("EmpInfoDto selectMemberInfo");
		SqlSession session = factory.openSession();
		return session.selectList(NS + "selectMemberInfo", map);
	}

	// 전체 회원 수
	public int countMemberInfo() {
		log.info("EmpInfoDto countMemberInfo");
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "countMemberInfo");
	}
	
	// 회원 삭제
	@Override
	public boolean memberDel(List<String> list) {
		log.info("EmpInfoDto memberDel {}", list);
		SqlSession session = factory.openSession(true);
		return (session.delete(NS + "memberDel", list) >= 1) ? true : false;
	}

	// 대기 회원 조회
	@Override
	public List<EmpInfoDto> waitingMember(Map<String, Object> map) {
		log.info("EmpInfoDto waitingMember");
		SqlSession session = factory.openSession();
		return session.selectList(NS + "waitingMember", map);
	}
	
	// 대기 회원 수
	@Override
	public int countWaiting() {
		log.info("EmpInfoDto countWaiting");
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "countWaiting");
	}
	
	
	
}
