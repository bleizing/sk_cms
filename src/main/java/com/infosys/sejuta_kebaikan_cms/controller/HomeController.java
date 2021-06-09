package com.infosys.sejuta_kebaikan_cms.controller;

import java.util.ArrayList;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.SejutaKebaikanCmsApplication;
import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

@Controller
@RequestMapping({"/pages/dashboard"})
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(SejutaKebaikanCmsApplication.class);
	
	@Autowired
	private CmsUserService cmsUserService;
	
	@GetMapping
	public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CmsUser cmsUser = cmsUserService.findCmsUserByUsername(auth.getName());
		cmsUserService.checkUserCmsMenu(cmsUser.getId());
		TreeMap<String, ArrayList<CmsMenu>> cmsMenuMap = ConstModel.getUserCmsMenuMap();
		modelAndView.addObject("userCmsMenu", cmsMenuMap);
        modelAndView.addObject("cmsUser", cmsUser);
        modelAndView.addObject("title", "Beranda");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("pages/home");
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
