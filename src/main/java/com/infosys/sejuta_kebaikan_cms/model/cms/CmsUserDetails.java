package com.infosys.sejuta_kebaikan_cms.model.cms;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CmsUserDetails implements UserDetails {
    private CmsUser cmsUser;
     
    public CmsUserDetails(CmsUser cmsUser) {
        this.cmsUser = cmsUser;
    }
 
    public CmsUser getCmsUser() {
		return cmsUser;
	}


	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}

	@Override
    public boolean isAccountNonLocked() {
        return cmsUser.isAccountNonLocked();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
