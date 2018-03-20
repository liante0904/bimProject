package com.bridgeimpact.renewal.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeimpact.renewal.dto.ArticleVO;
import com.bridgeimpact.renewal.dto.BoardVO;
import com.bridgeimpact.renewal.dto.MemberVO;
import com.bridgeimpact.renewal.service.ArticleService;
import com.bridgeimpact.renewal.service.BoardService;
import com.bridgeimpact.renewal.service.CommentService;
import com.bridgeimpact.renewal.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private BoardService boardService;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    
    
	@RequestMapping(value="/page")
	public String writeForm(Model model, HttpServletRequest request){
		return "/test/writeForm";
	}

	@RequestMapping(value="/boot")
	public String boot(Model model, HttpServletRequest request){
		return "/test/bootstrap";
	}

	
	/**
	 * 파일 업로드 요청 처리(단일 파일 업로드)
	 * @param multipartHttpServletRequest
	 * @return
	 */
    @RequestMapping(value = "/fileUpload")
    public String fileUp(MultipartHttpServletRequest multipartHttpServletRequest) {
        // 저장 경로 설정
        String root = multipartHttpServletRequest.getSession().getServletContext().getRealPath("/");
        String path = root+"resources/upload/";
         System.out.println(path);
        String newFileName = ""; // 업로드 되는 파일명
         
        File dir = new File(path);
        System.out.println("경로 여부 확인 : "+dir.isDirectory());
        System.out.println(path);
        if(!dir.isDirectory()){
            dir.mkdirs();
            System.out.println("디렉토리 생성 완료");
        }
         
        Iterator<String> files = multipartHttpServletRequest.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
                         
            MultipartFile mFile = multipartHttpServletRequest.getFile(uploadFile);
            String fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            newFileName = System.currentTimeMillis()+"."
                    +fileName.substring(fileName.lastIndexOf(".")+1);
             
            try {
                mFile.transferTo(new File(path+newFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         
        System.out.println("id : " + multipartHttpServletRequest.getParameter("id"));
        System.out.println("pw : " + multipartHttpServletRequest.getParameter("pw"));
         
        return "/test/writeForm";
    }
    

	/**
	 * 파일 업로드 요청 처리(다중 파일 업로드)
	 * @param multipartHttpServletRequest
	 * @return
	 */
    @RequestMapping(value = "/filesUpload")
    public String filesUp(HttpServletRequest request,MultipartHttpServletRequest multipartHttpServletRequest) {
        // 저장 경로 설정
        String root = multipartHttpServletRequest.getSession().getServletContext().getRealPath("/");
        String path = root+"resources/upload/";
         System.out.println(path);
        String newFileName = ""; // 업로드 되는 파일명
         
        File dir = new File(path);
        System.out.println("경로 여부 확인 : "+dir.isDirectory());
        System.out.println(path);
        if(!dir.isDirectory()){
            dir.mkdirs();
            System.out.println("디렉토리 생성 완료");
        }
         
        Iterator<String> files = multipartHttpServletRequest.getFileNames();
        while(files.hasNext()){
            String uploadFile = files.next();
                         
            MultipartFile mFile = multipartHttpServletRequest.getFile(uploadFile);
            String fileName = mFile.getOriginalFilename();
            System.out.println("실제 파일 이름 : " +fileName);
            newFileName = System.currentTimeMillis()+"."
                    +fileName.substring(fileName.lastIndexOf(".")+1);
             
            try {
                mFile.transferTo(new File(path+newFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         
        
        
		// 다중파일 로직구간
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		java.util.Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext())
		{
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String path1 = request.getSession().getServletContext().getRealPath("/");
			logger.info(path1 + mFile.getOriginalFilename());
			File file = new File(path1 + mFile.getOriginalFilename());
			
			if (mFile.getSize() != 0) // File Null Check
			{
				if (!file.exists()) // 경로상에 파일이 존재하지 않을 경우
				{
					if (file.getParentFile().mkdirs()) // 경로에 해당하는 디렉토리들을 생성
					{
						try {
							file.createNewFile(); // 이후 파일 생성
							logger.info(file.getAbsolutePath() + file.getName());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				try {
					mFile.transferTo(file); //임시로 저장된 multipartFile을 실제 파일로 전송
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		
        System.out.println("id : " + multipartHttpServletRequest.getParameter("id"));
        System.out.println("pw : " + multipartHttpServletRequest.getParameter("pw"));
         
        return "/test/writeForm";
    }

    @RequestMapping(value = "/download.bim")
    public ModelAndView download(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String vanillaPath = "C:\\upload\\";
        vanillaPath += "4e624c22518285.gif";
		String serverPath = request.getSession().getServletContext().getRealPath("/");
        serverPath += "zb_ad_106_1.png";
		logger.info("callDownload : " + serverPath);

        System.out.println(serverPath);
        File downloadFile = new File(serverPath);

        if (!downloadFile.canRead()) {
            throw new Exception("File can't read(파일을 찾을 수 없습니다)");
        }
           return new ModelAndView("downloadView", "downloadFile", downloadFile);
        // 첫번째 인자 : beanName(id), 두번쨰 인자 :  File Object,
    }


}


