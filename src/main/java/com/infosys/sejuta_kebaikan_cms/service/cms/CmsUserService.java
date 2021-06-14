package com.infosys.sejuta_kebaikan_cms.service.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;
import com.infosys.sejuta_kebaikan_cms.model.Merchant;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsGroupMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsGroupMenuRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsMenuRepository;
import com.infosys.sejuta_kebaikan_cms.repository.cms.CmsUserRepository;
import com.infosys.sejuta_kebaikan_cms.util.PasswordUtil;

@Service
public class CmsUserService {
	@Autowired
	private CmsUserRepository cmsUserRepository;
	
	public static final int MAX_FAILED_ATTEMPTS = 3;
    
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
	
	@Autowired
	private CmsGroupMenuRepository cmsGroupMenuRepository;
	
	@Autowired
	private CmsMenuRepository cmsMenuRepository;
	
	public CmsUser getUserById(Long id) {
		return cmsUserRepository.findByIdAndActive(id, true);
	}

	public CmsUser findCmsUserByUsername(String username) {
		return cmsUserRepository.findByUsernameAndActive(username, true);
	}
	
	public CmsUser addCmsUser(CmsUser cmsUser) {
		cmsUser.setActive(true);
		cmsUser.setPassword(PasswordUtil.hashPassword(cmsUser.getPassword()));
		
		return cmsUserRepository.save(cmsUser);
	}
	
	@Transactional
	public void editCmsUser(Long id, CmsUser cmsUser) {
		CmsUser cmsUserDb = getUserById(id);
		cmsUserDb.setName(cmsUser.getName());
		cmsUserDb.setEmail(cmsUser.getEmail());
		cmsUserDb.setPhoneNumber(cmsUser.getPhoneNumber());
		
		Merchant merchantDb = cmsUserDb.getMerchant();
		if (merchantDb != null) {
			Merchant merchant = cmsUser.getMerchant();
			merchantDb.setName(merchant.getName());
			merchantDb.setEmail(merchant.getEmail());
			merchantDb.setDescription(merchant.getDescription());
			merchantDb.setAddress(merchant.getAddress());
			merchantDb.setCallCenter(merchant.getCallCenter());
			merchantDb.setWebUrl(merchant.getWebUrl());
		}
		
		cmsUserRepository.save(cmsUserDb);
		
		ConstModel.setCmsUserLoggedIn(cmsUserDb);
	}
	
	public boolean emailExists(String email, Long id) {
		boolean emailExists = true;
		CmsUser cmsUserDb = getUserById(id);
		if (cmsUserDb.getEmail().equals(email)) {
			emailExists = false;
		}
		
		if (emailExists) {
			emailExists = cmsUserRepository.findByEmail(email) == null ? false : true;
		}
		
		return emailExists;
	}
	
	public boolean phoneNumberExists(String phoneNumber, Long id) {
		boolean phoneNumberExists = true;
		CmsUser cmsUserDb = getUserById(id);
		if (cmsUserDb.getPhoneNumber().equals(phoneNumber)) {
			phoneNumberExists = false;
		}
		
		if (phoneNumberExists) {
			phoneNumberExists = cmsUserRepository.findByPhoneNumber(phoneNumber) == null ? false : true;
		}
		
		return phoneNumberExists;
	}
	
	public void loginSuccess(CmsUser cmsUser) {
		cmsUser.setLastLoginAt(new Date());
		cmsUser.setFailedLoginAttempt(0);
		cmsUserRepository.save(cmsUser);
	}
	
	public void loginFailed(CmsUser cmsUser) {
		cmsUser.setFailedLoginAt(new Date());
		Integer failedLoginAttempt = cmsUser.getFailedLoginAttempt();
		failedLoginAttempt++;
		cmsUser.setFailedLoginAttempt(failedLoginAttempt);
		cmsUserRepository.save(cmsUser);
	}
	
	public void lock(CmsUser cmsUser) {
		cmsUser.setAccountNonLocked(false);
		cmsUser.setLockTime(new Date());
         
        cmsUserRepository.save(cmsUser);
    }
	
	public boolean unlockWhenTimeExpired(CmsUser cmsUser) {
        long lockTimeInMillis = cmsUser.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();
         
        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
        	cmsUser.setAccountNonLocked(true);
        	cmsUser.setLockTime(null);
        	cmsUser.setFailedLoginAttempt(0);
             
            cmsUserRepository.save(cmsUser);
             
            return true;
        }
         
        return false;
    }
	
	public void checkUserCmsMenu(Long userId) {
		TreeMap<String, ArrayList<CmsMenu>> cmsMenuMap = ConstModel.getUserCmsMenuMap();
		if (cmsMenuMap == null) {
			cmsMenuMap = new TreeMap<>();
		}
		
		if (cmsMenuMap.isEmpty()) {
			List<CmsGroupMenu> cmsGroupMenus = cmsGroupMenuRepository.findGroupMenuByUserId(userId);
			for (CmsGroupMenu cmsGroupMenu : cmsGroupMenus) {
				List<CmsMenu> cmsMenus = cmsGroupMenu.getCmsMenus();
				ArrayList<CmsMenu> cmsMenuArrayList = new ArrayList<>();
				for (CmsMenu cmsMenu : cmsMenus) {
					cmsMenuArrayList.add(cmsMenu);
				}
				cmsMenuMap.put(cmsGroupMenu.getName(), cmsMenuArrayList);
			}
			
			for (Entry<String, ArrayList<CmsMenu>> entry : cmsMenuMap.entrySet()) {
//				System.out.println("Group name = " + entry.getKey());
				for (CmsMenu cmsMenu : entry.getValue()) {
//					System.out.println("Menu name = " + cmsMenu.getName());
				}
			}
			ConstModel.setUserCmsMenuMap(cmsMenuMap);
		}
	}
	
	public void checkUserCmsRoleMenu(Long userId) {
		ArrayList<String> userCmsPathArrayList = ConstModel.getUserCmsPathArrayList();
		if (userCmsPathArrayList == null) {
			userCmsPathArrayList = new ArrayList<>();
		}
		
		if (userCmsPathArrayList.isEmpty()) {
			List<String> cmsMenuList = cmsMenuRepository.findUrlMenuByUserId(userId);
			userCmsPathArrayList.addAll(cmsMenuList);
			ConstModel.setUserCmsPathArrayList(userCmsPathArrayList);
		}
	}
}
