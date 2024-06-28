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
import com.min.edu.model.EduBoardDaoImpl;
import com.min.edu.model.IEduBoardDao;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -6079506230026092331L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("InsertBoardServlet GET");
		
		req.getRequestDispatcher("/WEB-INF/views/insert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("InsertBoardServlet POST");
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		EmpInfoDto loginDto = (EmpInfoDto)session.getAttribute("loginDto");
		
		String id = loginDto.getId();
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		IEduBoardDao dao = new EduBoardDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("title", title);
		map.put("content", content);
		boolean isc = dao.insertBoard(map);
		
		if(isc) {
			resp.sendRedirect("./allSelect.do");
		} else {
			Utility.servlet_alert(resp, "글 작성에 실패하여 글 작성으로 돌아갑니다", "insertBoard.do");
		}
		
	}
}
