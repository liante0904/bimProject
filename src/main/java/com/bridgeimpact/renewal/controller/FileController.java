package com.bridgeimpact.renewal.controller;

import com.bridgeimpact.renewal.dto.FileVO;
import com.bridgeimpact.renewal.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {
    
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    
    @ResponseBody
	@RequestMapping(value="/deleteFile.bim")
	public Map<String, String> writeForm(FileVO fileVO, HttpServletRequest request){

		boolean result = false;
    	Map<String, String> resultMap = new HashMap<String, String>();
		try {
			 result = fileService.deleteFile(request, fileVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* file 처리 결과 핸들링 */
		if (!result){
			resultMap.put("result", "error");
			return resultMap;
		}
		resultMap.put("result", "success");
		return resultMap;
	}


}


