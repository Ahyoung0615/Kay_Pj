package com.min.edu.ctrl;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.EduBoardDaoImpl;
import com.min.edu.model.IEduBoardDao;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiDeleteBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -6606372284402689174L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MultiDeleteBoardServlet POST");
		
		String[] seqArr = req.getParameterValues("chk");
		log.info("list {}", Arrays.asList(seqArr));
		
		IEduBoardDao dao = new EduBoardDaoImpl();
		boolean isc = dao.multiDeleteBoard(Arrays.asList(seqArr));
		
		if(isc) {
			resp.sendRedirect("./allSelect.do");
		} else {
			Utility.servlet_alert(resp, "삭제가 되지않았습니다", "allSelect.do");
		}
	}
}
