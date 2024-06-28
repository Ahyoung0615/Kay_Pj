package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.EmpInfoDto;
import com.min.edu.dto.PagingDto;
import com.min.edu.model.EmpInfoDaoImpl;
import com.min.edu.model.IEmpInfoDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 2327074598421776624L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MemberInfoServlet GET");
		
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		String page = req.getParameter("page");
		
		if(page == null) {
			page = "1";
		}
		int selectPage = Integer.parseInt(page);
		
		PagingDto pDto = new PagingDto();
		pDto.setTotalCount(dao.countMemberInfo()); 
		pDto.setCountList(5); 
		pDto.setCountPage(5); 
		pDto.setTotalPage(pDto.getTotalCount());
		pDto.setPage(selectPage);
		pDto.setStagePage(selectPage);
		pDto.setEndPage(pDto.getCountPage());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", pDto.getPage() * pDto.getCountList() - (pDto.getCountList() - 1));
		map.put("last", pDto.getPage() * pDto.getCountList());
		
		dao.selectMemberInfo(map);
		List<EmpInfoDto> list = dao.selectMemberInfo(map);
		
		req.setAttribute("page", pDto);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/memderInfoList.jsp").forward(req, resp);
	}
}
