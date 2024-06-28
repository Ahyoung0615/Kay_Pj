package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoDaoImpl;
import com.min.edu.model.IEmpInfoDao;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 3828164570372890792L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet GET");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet POST");
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("password", password);
		
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		EmpInfoDto loginDto = dao.login(map);
		
		log.info("로그인 정보 {}", loginDto);
		
		if(loginDto == null) {
			Utility.servlet_alert(resp, "회원 정보가 없습니다", "login.do");
		} else {
			HttpSession session = req.getSession();
			
			session.setAttribute("loginDto", loginDto);
			resp.sendRedirect("./allSelect.do");
		}
	}
}

