package com.min.edu.ctrl;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.EmpInfoDaoImpl;
import com.min.edu.model.IEmpInfoDao;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDelServlet extends HttpServlet {

	private static final long serialVersionUID = 2549562966957515280L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MemberDelServlet POST");
		
		String[] ids = req.getParameterValues("chk");
		
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		boolean isc = dao.memberDel(Arrays.asList(ids));
		
		if(isc) {
			resp.sendRedirect("./memberInfo.do");
		} else {
			Utility.servlet_alert(resp, "잘못된 요청입니다" , "memberInfo.do");
		}
	}
}
