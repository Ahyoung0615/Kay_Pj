package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.EduBoardDaoImpl;
import com.min.edu.model.IEduBoardDao;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 3231474244130530844L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DeleteBoardServlet POST");
		
		String seq = req.getParameter("seq");
		log.info("{}",seq);
		IEduBoardDao dao = new EduBoardDaoImpl();
		boolean isc = dao.deleteBoard(seq);
		if(isc) {
			resp.sendRedirect("./allSelect.do");
		} else {
			Utility.servlet_alert(resp, "삭제에 실패하셨습니다", "detailSelect.do?seq=" + seq);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DeleteBoardServlet GET");
		
		String seq = req.getParameter("seq");
		log.info("{}",seq);
		IEduBoardDao dao = new EduBoardDaoImpl();
		boolean isc = dao.deleteBoard(seq);
		if(isc) {
			resp.sendRedirect("./allSelect.do");
		} else {
			Utility.servlet_alert(resp, "삭제에 실패하셨습니다", "detailSelect.do?seq=" + seq);
		}
	}
}
