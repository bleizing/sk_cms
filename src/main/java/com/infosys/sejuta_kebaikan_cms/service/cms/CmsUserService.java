package com.infosys.sejuta_kebaikan_cms.service.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsGroupMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsGroupMenuRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsUserRepository;
import com.infosys.sejuta_kebaikan_cms.util.PasswordUtil;

@Service
public class CmsUserService {
	@Autowired
	private CmsUserRepository cmsUserRepository;
	
	@Autowired
	private CmsGroupMenuRepository cmsGroupMenuRepository;

	public CmsUser findCmsUserByUsername(String username) {
		return cmsUserRepository.findByUsername(username);
	}
	
	public CmsUser addCmsUser(CmsUser cmsUser) {
		cmsUser.setActive(true);
		cmsUser.setPassword(PasswordUtil.hashPassword(cmsUser.getPassword()));
		
		return cmsUserRepository.save(cmsUser);
	}
	
	public void checkUserCmsMenu(Long userId) {
		TreeMap<String, ArrayList<CmsMenu>> cmsMenuMap = ConstModel.getUserCmsMenuMap();
		if (cmsMenuMap == null) {
			cmsMenuMap = new TreeMap<>();
		}
		
		if (cmsMenuMap.isEmpty()) {
			List<CmsGroupMenu> cmsGroupMenus = cmsGroupMenuRepository.findGroupMenu(userId);
			for (CmsGroupMenu cmsGroupMenu : cmsGroupMenus) {
				List<CmsMenu> cmsMenus = cmsGroupMenu.getCmsMenus();
				ArrayList<CmsMenu> cmsMenuArrayList = new ArrayList<>();
				for (CmsMenu cmsMenu : cmsMenus) {
					cmsMenuArrayList.add(cmsMenu);
				}
				cmsMenuMap.put(cmsGroupMenu.getName(), cmsMenuArrayList);
			}
			
			for (Entry<String, ArrayList<CmsMenu>> entry : cmsMenuMap.entrySet()) {
				System.out.println("Group name = " + entry.getKey());
				for (CmsMenu cmsMenu : entry.getValue()) {
					System.out.println("Menu name = " + cmsMenu.getName());
				}
			}
			ConstModel.setUserCmsMenuMap(cmsMenuMap);
		}
	}
}
