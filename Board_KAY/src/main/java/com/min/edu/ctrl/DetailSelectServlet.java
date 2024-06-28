package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.model.EduBoardDaoImpl;
import com.min.edu.model.IEduBoardDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetailSelectServlet extends HttpServlet {

	private static final long serialVersionUID = -6955414335199032701L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DetailSelectServlet GET");
		
		String seq = req.getParameter("seq");
		
		IEduBoardDao dao = new EduBoardDaoImpl();
		EduBoardDto dto = dao.detailSelect(seq);
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/WEB-INF/views/detailBoard.jsp").forward(req, resp);
	}
}
