package com.infosys.sejuta_kebaikan_cms.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
 
    @Autowired
    private CmsUserService cmsUserService;
     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        ShopmeUserDetails userDetails =  (ShopmeUserDetails) authentication.getPrincipal();
//        User user = userDetails.getUser();
//        if (user.getFailedAttempt() > 0) {
//            userService.resetFailedAttempts(user.getEmail());
//        }
    	
    	String username = authentication.getName();
    	CmsUser cmsUser = cmsUserService.findCmsUserByUsername(username);
    	if (cmsUser.getActive() && cmsUser.isAccountNonLocked()) {
			cmsUserService.loginSuccess(cmsUser);
		}
    	
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
