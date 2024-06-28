package com.min.edu.ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import com.min.edu.dto.EmpInfoDto;
import com.min.edu.model.EmpInfoDaoImpl;
import com.min.edu.model.IEmpInfoDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingInServlet extends HttpServlet {

	private static final long serialVersionUID = 4365075618429563318L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("SingInServlet GET");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		req.getRequestDispatcher("/WEB-INF/views/singIn.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("FileBoardWrite 글 입력 POST");
		req.setCharacterEncoding("UTF-8");
		
//		String saveDirectory = "C:\\Programming_IDE\\eclipse_WEB\\workspace_test\\Board_KAY\\src\\main\\webapp\\upload";
		
		// 2) 상대 경로(context)
		ServletContext context = req.getServletContext();
		String saveDirectory = context.getRealPath("upload");
		
		log.info("경로 {}", saveDirectory);
			
		File directory = new File(saveDirectory);
		if(!directory.exists()) {
			boolean created = directory.mkdirs();
			if(created) {
				log.info("디렉토리 생성 {}", saveDirectory);
			} else {
				log.info("디렉토리 생성 실패");
			}
		}
		// 파일 저장을 위한 DiskFileItemFactory
		FileItemFactory factory = new DiskFileItemFactory();
		// 파일 업로드 핸들러 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 업로드 메모리 크기 제한 설정
		upload.setFileSizeMax(5*1024*1024); // 5MB
		
		try {
			// 파일 아이템 Iterator 생성
			FileItemIterator iter = upload.getItemIterator(req);
			
			// 전달 내용 객체 BoardVo
			EmpInfoDto empInfoDto = new EmpInfoDto();
			// 파일 리스트
			
			// iter 객체에 파일과 글자가 담겨있음. 판단에 따라서 각 객체에 맞는 타입으로 입력해주면 됨
			while (iter.hasNext()) { // 화면에서 받은 객체(req)가 들어(item)있다 
				FileItemStream item = iter.next();
				
				if(item.isFormField()) { // item 파일인지 글인지 판단 Form Field  
					String fileName = item.getFieldName();
					String fileValue = Streams.asString(item.openStream());
					
					log.info("파일이 아닌 경우 처리 - fileName: {}, fileValue: {}", fileName, fileValue);
					
					if("id".equals(fileName)) {
						empInfoDto.setId(fileValue);
					}else if("password".equals(fileName)) {
						empInfoDto.setPassword(fileValue);
					}else if("name".equals(fileName)) {
						empInfoDto.setName(fileValue);
					}else if("phone".equals(fileName)) {
						empInfoDto.setPhone(fileValue);
					}
					log.info("최종 DB에 입력되는 From 필드 값: {}", empInfoDto);
				}else if(!item.isFormField()) { // Data 파일
					// 파일인 경우
					String origin_fname = item.getName();
					if(origin_fname != null && !origin_fname.isEmpty()) {
						// 파일 저장 Dto
						String stored_fname = UUID.randomUUID().toString().replace("-", "")
											  + origin_fname.substring(origin_fname.lastIndexOf("."));
						
						File uploadedFile = new File(saveDirectory, stored_fname);
						InputStream inputStream = null;
						OutputStream outputStream = null;
						inputStream = item.openStream();
						outputStream = new FileOutputStream(uploadedFile);
						
						inputStream.transferTo(outputStream);
						
						empInfoDto.setFilename(stored_fname);
						
					}
				}
			}
			
			IEmpInfoDao dao = new EmpInfoDaoImpl();
			
			boolean isc = dao.singIn(empInfoDto);
			if(isc) {
				resp.sendRedirect("./login.do");
			}
			
		} catch (FileUploadException | IOException e) {
			e.printStackTrace();
		}
	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		log.info("SingInServlet POST");
//		
//		String saveDirectory = "C:\\temp1";
////		ServletContext context = req.getServletContext();
////		String saveDirectory = context.getRealPath("upload");
//		log.info("상대 경로 {}", saveDirectory);
//		
//		File directory = new File(saveDirectory);
//		
//		if(!directory.exists()) {
//			boolean created = directory.mkdirs();
//			if(created) {
//				log.info("디렉토리 생성 {}", saveDirectory);
//			} else {
//				log.info("디렉토리 생성 실패");
//			}
//		}
//		
//		FileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		upload.setSizeMax(5 * 1024 * 1024); 
//		
//		EmpInfoDto dto = new EmpInfoDto();
//		
//		try {
//			// 파일 아이템 Iterator 생성
//			FileItemIterator iter = upload.getItemIterator(req);
//			
//			
//			// iter 객체에 파일과 글자가 담겨있음. 판단에 따라서 각 객체에 맞는 타입으로 입력해주면 됨
//			while (iter.hasNext()) { // 화면에서 받은 객체(req)가 들어(item)있다 
//				FileItemStream item = iter.next();
//				
//				if(item.isFormField()) { // item 파일인지 글인지 판단 Form Field  
//					String fileName = item.getFieldName();
//					String fileValue = Streams.asString(item.openStream());
//					
//					log.info("파일이 아닌 경우 처리 - fileName: {}, fileValue: {}", fileName, fileValue);
//					
//					if("id".equals(fileName)) {
//						dto.setId(fileValue);
//					}else if("password".equals(fileName)) {
//						dto.setPassword(fileValue);
//					}else if("name".equals(fileName)) {
//						dto.setName(fileValue);
//					}else if("phone".equals(fileName)) {
//						dto.setPhone(fileValue);
//					}
//					
//					log.info("최종 DB에 입력되는 From 필드 값: {}", dto);
//					}else  { // Data 파일
//						// 파일인 경우
//						String fname = item.getName();
//						if(fname != null && !fname.isEmpty()) {
//							
//						String stored_fname = UUID.randomUUID().toString().replace("-", "")
//											+ fname.substring(fname.lastIndexOf("."));
//
//							// 파일 저장 Dto
//							File uploadedFile = new File(saveDirectory);
//							InputStream inputStream = null;
//							OutputStream outputStream = null;
//							inputStream = item.openStream();
//							outputStream = new FileOutputStream(uploadedFile);
//							
//							inputStream.transferTo(outputStream);
//							
//							// 파일 정보 추가하여 DB에 저장
//							dto.setFilename(fname);
//							
//							log.info("최종 DB에 입력되는 Data 필드 값: {}", dto);
//						}
//					}
//				}
//				log.info("upload Content \n  \n{}", dto);
//				
//				IEmpInfoDao dao = new EmpInfoDaoImpl();
//				
//				boolean isc = dao.singIn(dto);
//				if(isc) {
//					req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
//				} else {
//					Utility.servlet_alert(resp, "회원가입에 실패하셨습니다. 다시 시도해 주세요", "singIn.do");
//				}
//				} catch (FileNotFoundException e) {
//				    e.printStackTrace(); // Handle file not found error
//				} catch (IOException e) {
//				    e.printStackTrace(); // Handle IO error
//				} 
//				catch (FileUploadException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
}
