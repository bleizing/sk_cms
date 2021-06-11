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
	public ModelAndView home(@PathParam("showAlert") Boolean showAlert, @PathParam("alertAction") String alertAction, @PathParam("alertTitle") String alertTitle, @PathParam("alertType") String alertType, @PathParam("alertMessage") String alertMessage){
		logger.info("showAlert = " + showAlert);
        ModelAndView modelAndView = new ModelAndView();
        HashMap<String, Object> dataHashMap = new HashMap<>();
        if (showAlert != null && showAlert) {
        	dataHashMap = CommonUtil.dataHashMapAlert(dataHashMap, alertAction, alertTitle, alertType, alertMessage);
        }
        modelAndView = CommonUtil.setModelAndView(modelAndView, dataHashMap, "Beranda", "[dashboard]", "pages/home");
        return modelAndView;
    }
	
}
