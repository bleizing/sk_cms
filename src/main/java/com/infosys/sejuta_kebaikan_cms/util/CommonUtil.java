package com.infosys.sejuta_kebaikan_cms.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

public class CommonUtil {
	public static Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static boolean isUserLoggedIn() {
		boolean userLoggedIn = false;
		
		Authentication auth = getAuth();
		if (auth != null) {
			userLoggedIn = true;
		}
		
		return userLoggedIn;
	}
	
	
	public static CmsUser getUserLoggedIn(CmsUserService cmsUserService) {
		CmsUser cmsUser = null;
		Authentication auth = CommonUtil.getAuth();
		if (CommonUtil.isUserLoggedIn()) {
			cmsUser = cmsUserService.findCmsUserByUsername(auth.getName());
		}
		
		return cmsUser;
	}
}
