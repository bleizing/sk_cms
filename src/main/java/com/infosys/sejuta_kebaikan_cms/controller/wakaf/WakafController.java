package com.infosys.sejuta_kebaikan_cms.controller.wakaf;

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

import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;
import com.infosys.sejuta_kebaikan_cms.controller.donation.DonationController;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

@Controller
@RequestMapping({"/pages/wakaf"})
public class WakafController {
	private static final Logger logger = LoggerFactory.getLogger(DonationController.class);
	
	@Autowired
	private CmsUserService cmsUserService;
	
	@GetMapping("/list-wakaf")
	public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CmsUser cmsUser = cmsUserService.findCmsUserByUsername(auth.getName());
		cmsUserService.checkUserCmsMenu(cmsUser.getId());
		TreeMap<String, ArrayList<CmsMenu>> cmsMenuMap = ConstModel.getUserCmsMenuMap();
		modelAndView.addObject("userCmsMenu", cmsMenuMap);
        modelAndView.addObject("cmsUser", cmsUser);
        modelAndView.addObject("activeGroupMenu", "[wakaf]");
        modelAndView.addObject("title", "List Wakaf");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("pages/wakaf/list");
        return modelAndView;
    }
}
