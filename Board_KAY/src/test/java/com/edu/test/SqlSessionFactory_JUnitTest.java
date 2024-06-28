package com.edu.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.min.edu.database.SqlSessionFactoryManager;

public class SqlSessionFactory_JUnitTest {

	@Test
	public void sqlSessionFactoryManager_JUnitTest() {
		SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
		SqlSession session = factory.openSession();
		assertNotNull(session);
	}

}
