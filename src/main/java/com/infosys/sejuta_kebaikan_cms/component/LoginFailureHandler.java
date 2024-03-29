package com.infosys.sejuta_kebaikan_cms.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
   @Autowired
   private CmsUserService cmsUserService;
    
   @Override
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	   CmsUser cmsUser = ConstModel.getCmsUserLoggedIn();
       if (cmsUser != null) {
           if (cmsUser.isAccountNonLocked()) {
        	   // Max Attemp to Failed Login
               if (cmsUser.getFailedLoginAttempt() < cmsUserService.MAX_FAILED_ATTEMPTS - 1) {
            	   cmsUserService.loginFailed(cmsUser);
               } else {
                   cmsUserService.lock(cmsUser);
                   exception = new LockedException("Akun Anda Terkunci Setelah 3 Kali Gagal Login. Harap Hubungi Admin");
               }
//           } else {
//        	   // Unlock Account
//               if (cmsUserService.unlockWhenTimeExpired(cmsUser)) {
//                   exception = new LockedException("Your account has been unlocked. Please try to login again.");
//               }
           }
       }
       ConstModel.setCmsUserLoggedIn(null);
       
       super.setDefaultFailureUrl("/login?error");
       super.onAuthenticationFailure(request, response, exception);
   }
}
