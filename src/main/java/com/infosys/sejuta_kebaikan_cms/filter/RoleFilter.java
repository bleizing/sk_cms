package com.infosys.sejuta_kebaikan_cms.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.util.CommonUtil;

@Component
public class RoleFilter extends HttpFilter {
	private static final Logger logger = LoggerFactory.getLogger(RoleFilter.class);
	
	@Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
        throws IOException, ServletException {
		String path = request.getRequestURI();
		if (path.startsWith("/pages/")) {
			logger.info("RoleFilter");
			logger.info("path = " + path);
			Authentication auth = CommonUtil.getAuth();
			CmsUser cmsUser = CommonUtil.getUserLoggedIn();
			boolean isValid = isRoleMenuValid(path);
			
			if (!isValid && !path.contains("/dashboard")) {
				logger.info("Not Valid");
				((HttpServletResponse) response).sendRedirect("/pages/dashboard");
				return;
			}
		}

        chain.doFilter(request, response);
    }
	
	private boolean isRoleMenuValid(String path) {
		boolean valid = false;
		
		ArrayList<String> userCmsRoleMenuArrayList = ConstModel.getUserCmsPathArrayList();
		if (userCmsRoleMenuArrayList != null && userCmsRoleMenuArrayList.size() > 0) {
			for (String url : userCmsRoleMenuArrayList) {
				if (url.equals(path)) {
					valid = true;
					break;
				}
				
			}
		}
		return valid;
	}
}
