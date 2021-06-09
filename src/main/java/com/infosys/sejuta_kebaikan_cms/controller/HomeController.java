package com.infosys.sejuta_kebaikan_cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.SejutaKebaikanCmsApplication;
import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Controller
@RequestMapping({"/pages/dashboard"})
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(SejutaKebaikanCmsApplication.class);
		
	@GetMapping
	public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView = CommonUtil.setModelAndView(modelAndView, null, "Beranda", "[dashboard]", "pages/home");
        
        return modelAndView;
    }
	
	@GetMapping("/login")
	public ModelAndView login(){
		logger.info("login page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }
}
