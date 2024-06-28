package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EduBoardDto;
import com.min.edu.dto.PagingDto;
import com.min.edu.model.EduBoardDaoImpl;
import com.min.edu.model.IEduBoardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllSelectServlet extends HttpServlet {

	private static final long serialVersionUID = 4770679623244580955L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("AllSelectServlet GET");
		
		IEduBoardDao dao = new EduBoardDaoImpl();
		
		String page = req.getParameter("page");
		
		if(page == null) {
			page = "1";
		}
		int selectPage = Integer.parseInt(page);
		
		PagingDto pDto = new PagingDto();
		pDto.setTotalCount(dao.countBoard()); // 전체 글의 row 갯수
		pDto.setCountList(5); // 한 페이지에 포함되어 있는 row의 갯수
		pDto.setCountPage(5); // 그룹
		pDto.setTotalPage(pDto.getTotalCount());
		pDto.setPage(selectPage);
		pDto.setStagePage(selectPage);
		pDto.setEndPage(pDto.getCountPage());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", pDto.getPage() * pDto.getCountList() - (pDto.getCountList() - 1));
		map.put("last", pDto.getPage() * pDto.getCountList());
		List<EduBoardDto> list = dao.allSelect(map);
		
		req.setAttribute("page", pDto);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
	}
}
