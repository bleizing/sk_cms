package com.infosys.sejuta_kebaikan_cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

@Controller
@RequestMapping({"/", "/login"})
public class AuthController {
	@Autowired
	private CmsUserService cmsUserService;
	
	@GetMapping
	public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
	
//	@PostMapping("/registration")
//	public ModelAndView processLogin(@Validated CmsUser cmsUser, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        CmsUser cmsUserExist = cmsUserService.findCmsUserByEmail(cmsUser.getEmail());
////        if (cmsUserExist != null) {
////            bindingResult
////                    .rejectValue("email", "error.user",
////                            "Email sudah terdaftar");
////        }
////        if (bindingResult.hasErrors()) {
////            modelAndView.setViewName("/pages/admin/add_cms_user");
////        } else {
////            cmsUserService.addCmsUser(cmsUser);
//            modelAndView.addObject("cmsUser", cmsUserExist);
//            modelAndView.setViewName("/pages/home");
//
////        }
//        return modelAndView;
//    }
}