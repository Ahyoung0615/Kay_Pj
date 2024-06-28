package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.EduBoardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EduBoardDaoImpl implements IEduBoardDao {

	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.EduBoardDaoImpl.";
 	
	
	// 전체조회
	@Override
	public List<EduBoardDto> allSelect(Map<String, Object> map) {
		log.info("EduBoardDaoImpl allSelect");
		SqlSession session = factory.openSession();
		return session.selectList(NS + "allSelect", map);
	}

	// 전체 글 갯수
	@Override
	public int countBoard() {
		log.info("EduBoardDaoImpl countBoard");
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "countBoard");
	}
	
	// 내 글 조회
	@Override
	public List<EduBoardDto> selectMyPost(Map<String, Object> map) {
		log.info("EduBoardDaoImpl selectMyPost");
		SqlSession session = factory.openSession();
		return session.selectList(NS + "selectMyPost", map);
	}

	// 내 글 갯수
	@Override
	public int countMyPost(String id) {
		log.info("EduBoardDaoImpl countMyPost");
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "countMyPost", id);
	}

	// 상세 조회
	@Override
	public EduBoardDto detailSelect(String seq) {
		log.info("EduBoardDaoImpl detailSelect");
		SqlSession session = factory.openSession();
		return session.selectOne(NS + "detailSelect", seq);
	}

	// 입력
	@Override
	public boolean insertBoard(Map<String, Object> map) {
		log.info("EduBoardDaoImpl insertBoard");
		SqlSession session = factory.openSession(true);
		return (session.insert(NS + "insertBoard", map) == 1) ? true : false;
	}

	// 수정
	@Override
	public boolean updateBoard(Map<String, Object> map) {
		log.info("EduBoardDaoImpl updateBoard");
		SqlSession session = factory.openSession(true);
		return (session.update(NS + "updateBoard", map) == 1) ? true : false;
	}

	// 삭제
	@Override
	public boolean deleteBoard(String seq) {
		log.info("EduBoardDaoImpl deleteBoard");
		SqlSession session = factory.openSession(true);
		return (session.delete(NS + "deleteBoard", seq) == 1) ? true : false;
	}

	// 다중 삭제
	@Override
	public boolean multiDeleteBoard(List<String> list) {
		log.info("EduBoardDaoImpl multiDeleteBoard");
		SqlSession session = factory.openSession(true);
		return (session.delete(NS + "multiDeleteBoard", list) > 0) ? true : false;
	}
}
