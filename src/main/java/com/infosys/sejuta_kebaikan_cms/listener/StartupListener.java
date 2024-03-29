package com.infosys.sejuta_kebaikan_cms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;
import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {
	private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);
	
	@Autowired
	private CmsUserService cmsUserService;
	
	@Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("Startup Listener");
		CommonUtil.setCmsUserService(cmsUserService);
    }
}
