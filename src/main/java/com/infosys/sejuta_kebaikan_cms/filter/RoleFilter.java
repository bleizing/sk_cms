package com.infosys.sejuta_kebaikan_cms.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;

@Component
public class RoleFilter extends HttpFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
        throws IOException, ServletException {
		String path = request.getRequestURI();
		if (path.startsWith("/pages/")) {
			boolean isValid = isRoleMenuValid(path);
			
			if (!isValid && !path.contains("/dashboard")) {
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
