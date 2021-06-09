package com.infosys.sejuta_kebaikan_cms.controller.donation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Controller
@RequestMapping({"/pages/campaign"})
public class DonationController {
	private static final Logger logger = LoggerFactory.getLogger(DonationController.class);
	
	@GetMapping("/list-donasi")
	public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = CommonUtil.setModelAndView(modelAndView, null, "List Donasi", "[donasi]", "pages/donasi/list");
        return modelAndView;
    }
	
	@GetMapping("/transaksi-donasi")
	public ModelAndView transaksi() {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView = CommonUtil.setModelAndView(modelAndView, null, "Transaksi Donasi", "[donasi]", "pages/donasi/transaksi");
        return modelAndView;
	}
}
