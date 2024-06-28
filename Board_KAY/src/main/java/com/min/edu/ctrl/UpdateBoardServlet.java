package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EduBoardDaoImpl;
import com.min.edu.model.IEduBoardDao;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -1429409122714923401L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("UpdateBoardServlet GET");
		
		String seq = req.getParameter("seq");
		
		HttpSession session = req.getSession();
		EmpInfoDto loginDto = (EmpInfoDto)session.getAttribute("loginDto");
		
		IEduBoardDao dao = new EduBoardDaoImpl();
		EduBoardDto dto = dao.detailSelect(seq);
		
		if(loginDto.getId().equals(dto.getId())) {
			req.setAttribute("dto", dto);
			req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
		} else {
			Utility.servlet_alert(resp, "잘못된 접근입니다", "detailSelect.do");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("UpdateBoardServlet POST");
		req.setCharacterEncoding("UTF-8");
		
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		
		IEduBoardDao dao = new EduBoardDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("content", content);
		map.put("seq", seq);
		
		boolean isc = dao.updateBoard(map);
		if(isc) {
			
			resp.sendRedirect("./detailSelect.do?seq=" + seq);
		} else {
			Utility.servlet_alert(resp, "수정에 실패하여 다시 돌아갑니다", "updateBoard.do");
		}
	}

	
}
