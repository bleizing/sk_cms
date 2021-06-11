package com.infosys.sejuta_kebaikan_cms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.sejuta_kebaikan_cms.constant.ConstModel;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;
import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserService;

public class CommonUtil {
	private static CmsUserService cmsUserService;
	
	public static void setCmsUserService(CmsUserService cmsUserService) {
		CommonUtil.cmsUserService = cmsUserService;
	}

	public static Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static boolean isUserLoggedIn() {
		boolean userLoggedIn = false;
		
		Authentication auth = getAuth();
		if (auth != null) {
			userLoggedIn = true;
		}
		
		return userLoggedIn;
	}
	
	public static CmsUser getUserLoggedIn() {
		CmsUser cmsUser = ConstModel.getCmsUserLoggedIn();
		Authentication auth = CommonUtil.getAuth();
		if (cmsUser == null && CommonUtil.isUserLoggedIn()) {
			cmsUser = CommonUtil.cmsUserService.findCmsUserByUsername(auth.getName());
			ConstModel.setCmsUserLoggedIn(cmsUser);
		}
		
		return cmsUser;
	}
	
	public static ModelAndView setModelAndView(ModelAndView modelAndView, HashMap<String, Object> dataHashMap, String title, String activeGroupMenu, String viewName) {
		CmsUser cmsUser = getUserLoggedIn();
		
		if (!CommonUtil.isModelExists()) {
			cmsUserService.checkUserCmsMenu(cmsUser.getId());
			cmsUserService.checkUserCmsRoleMenu(cmsUser.getId());
		}
		
		TreeMap<String, ArrayList<CmsMenu>> cmsMenuMap = ConstModel.getUserCmsMenuMap();
		
		if (dataHashMap != null && !dataHashMap.isEmpty()) {
			for (Entry<String, Object> entry : dataHashMap.entrySet()) {
				modelAndView.addObject(entry.getKey(), entry.getValue());
			}
		}
		
		CommonUtil.preparedModelAndView(modelAndView, title, activeGroupMenu, viewName);
		modelAndView.addObject("userCmsMenu", cmsMenuMap);
        modelAndView.addObject("cmsUser", cmsUser);
		return modelAndView;
	}
	
	public static ModelAndView preparedModelAndView(ModelAndView modelAndView, String title, String activeGroupMenu, String viewName) {
        modelAndView.addObject("activeGroupMenu", activeGroupMenu);
        modelAndView.addObject("title", title);
        modelAndView.setViewName(viewName);
        return modelAndView;
	}
	
	public static ModelAndView alertModelAndView(ModelAndView modelAndView, String action, String title, String type, String message) {
		modelAndView.addObject("showAlert", true);
		modelAndView.addObject("alertAction", action);
		modelAndView.addObject("alertTitle", title);
		modelAndView.addObject("alertType", type);
		modelAndView.addObject("alertMessage", message);
		return modelAndView;
	}

	
	public static ModelAndView alertCreateSuccess(ModelAndView modelAndView) {
		CommonUtil.alertModelAndView(modelAndView, "Create", "Success", "success", "Berhasil Menambahkan Data");
		return modelAndView;
	}
	
	public static ModelAndView alertCreateFailed(ModelAndView modelAndView) {
		CommonUtil.alertModelAndView(modelAndView, "Create", "Failed", "danger", "Gagal Menambahkan Data");
		return modelAndView;
	}
	
	public static ModelAndView alertUpdateSuccess(ModelAndView modelAndView) {
		CommonUtil.alertModelAndView(modelAndView, "Update", "Success", "success", "Berhasil Mengubah Data");
		return modelAndView;
	}
	
	public static ModelAndView alertUpdateFailed(ModelAndView modelAndView) {
		CommonUtil.alertModelAndView(modelAndView, "Update", "Failed", "danger", "Gagal Mengubah Data");
		return modelAndView;
	}
	
	public static ModelAndView alertDeleteSuccess(ModelAndView modelAndView) {
		CommonUtil.alertModelAndView(modelAndView, "Delete", "Success", "success", "Berhasil Menghapus Data");
		return modelAndView;
	}
	
	public static ModelAndView alertDeleteFailed(ModelAndView modelAndView) {
		CommonUtil.alertModelAndView(modelAndView, "Delete", "Failed", "danger", "Gagal Menghapus Data");
		return modelAndView;
	}
	
	public static HashMap<String, Object> dataHashMapAlert(HashMap<String, Object> dataHashMap, String action, String title, String type, String message) {
		dataHashMap.put("showAlert", true);
		dataHashMap.put("alertAction", action);
        dataHashMap.put("alertType", type);
        dataHashMap.put("alertTitle", title);
        dataHashMap.put("alertMessage", message);
        return dataHashMap;
	}
	
	public static boolean isModelExists() {
		if (ConstModel.getCmsUserLoggedIn() == null || (ConstModel.getUserCmsMenuMap() == null && ConstModel.getUserCmsPathArrayList() == null)) {
			return false;
		}
		return true;
	}
	
	public static void clearModelExists() {
		ConstModel.setCmsUserLoggedIn(null);
		ConstModel.setUserCmsMenuMap(null);
		ConstModel.setUserCmsPathArrayList(null);
	}
	
	public static boolean isValidNumberValue(String string) {
		return string.matches("\\d+");
	}
}
