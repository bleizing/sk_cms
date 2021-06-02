package com.infosys.sejuta_kebaikan_cms.service.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsUserRepository;
import com.infosys.sejuta_kebaikan_cms.util.PasswordUtil;

@Service
public class CmsUserService {
	@Autowired
	private CmsUserRepository cmsUserRepository;

	public CmsUser findCmsUserByEmail(String email) {
		return cmsUserRepository.findByEmail(email);
	}
	
	public CmsUser addCmsUser(CmsUser cmsUser) {
		cmsUser.setActive(true);
		cmsUser.setPassword(PasswordUtil.hashPassword(cmsUser.getPassword()));
		
		return cmsUserRepository.save(cmsUser);
	}
}
