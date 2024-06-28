package com.min.edu.database;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlSessionFactoryManager {

	private static SqlSessionFactory factory;

	static {
		String path = "configuration/configuration.xml";
		try (Reader reader = Resources.getResourceAsReader(path)) {
			factory = new SqlSessionFactoryBuilder().build(reader);
			log.info("SqlSessionFactory 생성");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
