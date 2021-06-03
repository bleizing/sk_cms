package com.infosys.sejuta_kebaikan_cms.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsRoleMenuService;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

@Component
public class RoleFilter extends HttpFilter {
	@Autowired
	private CmsRoleMenuService cmsRoleMenuService;
	
	@Autowired
	private CmsUserService cmsUserService;
	
	@Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
        throws IOException, ServletException {
		String path = request.getRequestURI();
		if (path.startsWith("/pages/")) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CmsUser cmsUser = cmsUserService.findCmsUserByEmail(auth.getName());
			HashMap<Long, String> cmsRoleMenuMap = cmsRoleMenuService.getAllData();
			boolean isValid = cmsRoleMenuService.isRoleMenuValid(cmsUser.getCmsRole().getId(), path);
			
			if (!isValid) {
				((HttpServletResponse) response).sendRedirect("/pages");
				return;
			}
			
		}

        chain.doFilter(request, response);
    }
}