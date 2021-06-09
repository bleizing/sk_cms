package com.infosys.sejuta_kebaikan_cms.service.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsRoleMenuRepository;

@Service
public class CmsRoleMenuService {
	
	@Autowired
	private CmsRoleMenuRepository cmsRoleMenuRepository;
}
