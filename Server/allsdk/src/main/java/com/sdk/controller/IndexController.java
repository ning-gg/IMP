package com.sdk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sdk.service._91SdkService;


@Controller
public class IndexController {
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Resource
	_91SdkService categoryService;

    @RequestMapping("/index")
	public ModelAndView pageIndex(HttpServletRequest request, HttpServletResponse response){    	
    	
    	Map<String, Object> model = new HashMap<String, Object>();
    	    	
    	return new ModelAndView("/index", model);
	}
}