package com.infosys.sejuta_kebaikan_cms.controller.wakaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.controller.donation.DonationController;
import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Controller
@RequestMapping({"/pages/wakaf"})
public class WakafController {
	private static final Logger logger = LoggerFactory.getLogger(DonationController.class);
	
	@GetMapping("/list-wakaf")
	public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = CommonUtil.setModelAndView(modelAndView, null, "List Wakaf", "[wakaf]", "pages/wakaf/list");
        return modelAndView;
    }
}
