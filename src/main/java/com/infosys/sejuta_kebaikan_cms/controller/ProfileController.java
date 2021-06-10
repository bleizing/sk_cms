package com.infosys.sejuta_kebaikan_cms.controller;

import java.util.HashMap;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;
import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Controller
@RequestMapping({"/pages/profile"})
public class ProfileController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private CmsUserService cmsUserService;
	
	@GetMapping("/edit")
	public ModelAndView edit(@PathParam("id") Long id){
		logger.info("edit profile");
        ModelAndView modelAndView = new ModelAndView();
        HashMap<String, Object> dataHashMap = new HashMap<>();
        dataHashMap.put("cmsUserId", id);
        modelAndView = CommonUtil.setModelAndView(modelAndView, dataHashMap, "Edit Profile", "[profile]", "pages/profile/edit");
        
        return modelAndView;
    }
	
	@PostMapping("/edit")
	public ModelAndView saveEdit(@PathParam("id") Long id, @ModelAttribute("cmsUser") CmsUser cmsUser, BindingResult result) {
		logger.info("save edit profile");
		ModelAndView modelAndView = new ModelAndView();
		
		String viewName = "redirect:/pages/dashboard";
        modelAndView.setViewName(viewName);
        
        cmsUserService.editCmsUser(id, cmsUser);
        
        if (result.hasErrors()) {
        	viewName = "pages/profile/edit?id=" + id;
            modelAndView = CommonUtil.setModelAndView(modelAndView, null, "Edit Profile", "[profile]", viewName);
	    }
        
        return modelAndView;
	}
}
