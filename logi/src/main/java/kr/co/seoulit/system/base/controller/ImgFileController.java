package kr.co.seoulit.system.base.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.seoulit.hr.affair.serviceFacade.HrServiceFacade;
import kr.co.seoulit.hr.affair.to.EmployeeDetailTO;

@RestController
@RequestMapping("/base/*")
public class ImgFileController {
	
	@Autowired
	private HrServiceFacade hrSF;

	// 서버에 저장되는 파일 경로
	private static String serverUploadFolderPath = "img\\empPhoto\\";
	// 뷰에서 file 태그로 전송할 때 name 속성의 값
	private static String multipartFileName = "uploadImgFile";	
	private ModelMap modelMap = new ModelMap();
	
	String newFileName, originalFileExtension, contentType;

	@RequestMapping("/imgFileUpload.do")
	public ModelAndView imgFileUpload(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		
		String root = "C:\\dev\\http\\Apache24\\htdocs\\";

		// 파일 업로드 경로	
		String uploadPath = root + serverUploadFolderPath;  
    	
		File file = new File(uploadPath);
		if(file.exists() == false){
			file.mkdirs(); // 위 경로가 없다면 디렉토리를 만들어라
		}

		// multipartFileName 에 해당하는 업로드 파일 정보
		MultipartFile multipartFile = multipartHttpServletRequest.getFile(multipartFileName);
		
		if (!multipartFile.isEmpty()) {
			
		contentType = multipartFile.getContentType();  // 확장자를 구한다
		
			if(contentType.contains("image/jpeg")) {
				originalFileExtension = ".jpg";
			}
			else if(contentType.contains("image/png")) {
				originalFileExtension = ".png";
			}
			else if(contentType.contains("image/gif")) {
				originalFileExtension = ".gif";
			}
			else if(contentType.contains("image/svg+xml")) {
				originalFileExtension = ".svg";
			}
			else if(contentType.contains("image/tiff")) {
				originalFileExtension = ".tif";
			}
			else if(contentType.contains("image/webp")) {
				originalFileExtension = ".webp";
			}
		}	

			// 중복되지 않도록 새로운 파일명 생성 	
			String empCode = multipartHttpServletRequest.getParameter("empCode");	
			newFileName = empCode + "-" + Long.toString(System.nanoTime()) + originalFileExtension;
			
			System.out.println("------------- 이미지 업로드 start -------------");
			System.out.println("   @ file 태그 name 속성값 : " + multipartFile.getName());
			System.out.println("   @ 원본 파일명 : " + multipartFile.getOriginalFilename());
			System.out.println("   @ 저장 파일명 : " + newFileName);
			System.out.println("   @ 파일 크기 : " + multipartFile.getSize());

			// 파일 객체 생성
			file = new File(uploadPath, newFileName);
			System.out.println("file->"+file);
			
			try {
				// 업로드된 파일 객체를 transferTo() 메소드로 저장
				multipartFile.transferTo(file);

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			EmployeeDetailTO employeeDetailTO = new EmployeeDetailTO();		
			employeeDetailTO.setEmpCode(empCode);
			employeeDetailTO.setImage(newFileName);
			
			hrSF.updateEmpImg(employeeDetailTO); 
			
			modelMap.clear();

			modelMap.put("imgUrl", "/" + serverUploadFolderPath + newFileName);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "성공");				

		return new ModelAndView("redirect:/hr/empImg.html", modelMap);
	}
}
