package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoDaoImpl;
import com.min.edu.model.IEmpInfoDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdCheck extends HttpServlet {

	private static final long serialVersionUID = -2584273547135779736L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("IdCheck POST");
		
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		String id = req.getParameter("id");
		log.info("id {}", id);
//		EmpInfoDto idCheck = dao.idCheck(id);
		
		
//		if(idCheck == null) {
//			req.setAttribute("id", id);
			
//		}
		
	}
}
