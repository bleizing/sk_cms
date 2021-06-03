package com.infosys.sejuta_kebaikan_cms.service.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsRoleMenu;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsRoleMenuRepository;

@Service
public class CmsRoleMenuService {
	
	@Autowired
	private CmsRoleMenuRepository cmsRoleMenuRepository;
	
	private HashMap<Long, String> cmsRoleMenuMap;
	
	public void setInitData() {
		List<CmsRoleMenu> cmsRoleMenuList = null;
		if (cmsRoleMenuMap == null) {
			cmsRoleMenuMap = new HashMap<>();
			cmsRoleMenuList = new ArrayList<CmsRoleMenu>();
		}
		
		if (cmsRoleMenuMap.size() == 0) {
			cmsRoleMenuList = cmsRoleMenuRepository.findByActive(true);
			for (CmsRoleMenu cmsRoleMenu : cmsRoleMenuList) {
				cmsRoleMenuMap.put(cmsRoleMenu.getCmsRole().getId(), cmsRoleMenu.getCmsMenu().getUrl());
			}
		}
	}
	
	public HashMap<Long, String> getAllData() {
		setInitData();
		
		return cmsRoleMenuMap;
	}
	
	public Boolean isRoleMenuValid(Long roleId, String path) {
		boolean valid = false;
		setInitData();
		
		for (Map.Entry<Long, String> entry : cmsRoleMenuMap.entrySet()) {
			if (roleId.equals(entry.getKey()) && path.equals(entry.getValue())) {
				valid = true;
				break;
			}
		}
		
		return valid;
	}

}
