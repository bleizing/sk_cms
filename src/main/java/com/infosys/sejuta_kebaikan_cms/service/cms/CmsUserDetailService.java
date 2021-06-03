package com.infosys.sejuta_kebaikan_cms.service.cms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsRole;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;

@Service
public class CmsUserDetailService implements UserDetailsService {
	@Autowired
	private CmsUserService cmsUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CmsUser cmsUser = cmsUserService.findCmsUserByEmail(username);
		
		if (cmsUser == null) {
	        throw new UsernameNotFoundException(username);
	    }
		
		List<GrantedAuthority> authorities = getUserAuthority(cmsUser.getCmsRole());
        return buildUserForAuthentication(cmsUser, authorities);
	}
	
	private List<GrantedAuthority> getUserAuthority(CmsRole cmsRole) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//        for (CmsRole cmsRole : cmsRoles) {
//            roles.add(new SimpleGrantedAuthority(cmsRole.getCategory()));
//        }
        
        roles.add(new SimpleGrantedAuthority(cmsRole.getCategory()));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(CmsUser cmsUser, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(cmsUser.getEmail(), cmsUser.getPassword(), cmsUser.getActive(), true, true, true, authorities);
    }

}
