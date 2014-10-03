package com.doorcii.ad.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试首页
 * @author Jacky
 */
@Controller
public class IndexController extends BaseController {
	
	@RequestMapping("index.htm")
	public ModelAndView index_page(HttpServletRequest request,ModelMap modelMap) {
		modelMap.put("testkey", "我是一条柴！");
		
		return new ModelAndView("other_index",modelMap);
	}
}
