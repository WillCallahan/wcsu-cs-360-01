package edu.wcsu.cs.dating.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	public IndexController() {

	}

	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		return modelAndView;
	}

}