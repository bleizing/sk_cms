package com.infosys.sejuta_kebaikan_cms.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Controller
@RequestMapping({"/", "/login"})
public class AuthController {
	
	@GetMapping
	public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            modelAndView.setViewName("login");
            CommonUtil.clearModelExists();
        } else {
        	modelAndView.setViewName("redirect:/pages/dashboard");
        }
 
        return modelAndView;
    }
	
}
