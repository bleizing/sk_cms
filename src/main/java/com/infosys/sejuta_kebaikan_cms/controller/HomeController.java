package com.infosys.sejuta_kebaikan_cms.controller;

import java.util.HashMap;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Controller
@RequestMapping({"/pages/dashboard"})
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
		
	@GetMapping
	public ModelAndView home(@PathParam("test") String test){
		logger.info("test = " + test);
        ModelAndView modelAndView = new ModelAndView();
        HashMap<String, Object> dataHashMap = new HashMap<>();
        if (test != null && !test.equals("")) {
	        dataHashMap.put("alertType", "warning");
	        dataHashMap.put("alertTitle", "WARNING!");
	        dataHashMap.put("alertMessage", test);
        }
        modelAndView = CommonUtil.setModelAndView(modelAndView, dataHashMap, "Beranda", "[dashboard]", "pages/home");
        return modelAndView;
    }
	
}
