package com.infosys.sejuta_kebaikan_cms.service.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsRoleMenu;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsRoleMenuRepository;

@Service
public class CmsRoleMenuService {
	
	@Autowired
	private CmsRoleMenuRepository cmsRoleMenuRepository;
	
	private ArrayList<String> menuArrayList = new ArrayList<>();
	private HashMap<Long, ArrayList<String>> cmsRoleMenuMap;
	
	public void setInitData() {
		List<CmsRoleMenu> cmsRoleMenuList = null;
		if (cmsRoleMenuMap == null) {
			cmsRoleMenuMap = new HashMap<>();
			cmsRoleMenuList = new ArrayList<CmsRoleMenu>();
		}
		
		if (cmsRoleMenuMap.size() == 0) {
			cmsRoleMenuList = cmsRoleMenuRepository.findByActive(true);
			Long cmsRoleId = null;
			menuArrayList.clear();
			for (CmsRoleMenu cmsRoleMenu : cmsRoleMenuList) {
				cmsRoleId = cmsRoleMenu.getCmsRole().getId();
				menuArrayList.add(cmsRoleMenu.getCmsMenu().getUrl());
			}
			
			if (cmsRoleId != null) {
				cmsRoleMenuMap.put(cmsRoleId, menuArrayList);
			}
		}
	}
}
