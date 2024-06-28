package com.min.edu.ctrl;

import java.io.IOException;
import java.util.Arrays;
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
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberSuccessServlet extends HttpServlet {

	private static final long serialVersionUID = 4643466984134227797L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MemberSuccessServlet GET");
		
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		String page = req.getParameter("page");
		
		if(page == null) {
			page = "1";
		}
		int selectPage = Integer.parseInt(page);
		
		PagingDto pDto = new PagingDto();
		pDto.setTotalCount(dao.countWaiting()); 
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
		List<EmpInfoDto> list = dao.waitingMember(map);
		
		req.setAttribute("page", pDto);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/waitingList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("MemberSuccessServlet POST");
		
		IEmpInfoDao dao = new EmpInfoDaoImpl();
		
		String[] ids = req.getParameterValues("chk");
		boolean isc = dao.memberSuccess(Arrays.asList(ids));
		
		if(isc) {
			resp.sendRedirect("./memberSuccess.do");
		}else {
			Utility.servlet_alert(resp, "잘못된 요청입니다", "memberSuccess.do");
		}
	}
}
