package com.infosys.sejuta_kebaikan_cms.constant;

import java.util.ArrayList;
import java.util.TreeMap;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;
import com.infosys.sejuta_kebaikan_cms.model.cms.CmsUser;

public class ConstModel {
	private static CmsUser cmsUserLoggedIn;
	private static ArrayList<String> userCmsPathArrayList;
	private static TreeMap<String, ArrayList<CmsMenu>> userCmsMenuMap;
	public static CmsUser getCmsUserLoggedIn() {
		return cmsUserLoggedIn;
	}
	public static void setCmsUserLoggedIn(CmsUser cmsUserLoggedIn) {
		ConstModel.cmsUserLoggedIn = cmsUserLoggedIn;
	}
	public static ArrayList<String> getUserCmsPathArrayList() {
		return userCmsPathArrayList;
	}
	public static void setUserCmsPathArrayList(ArrayList<String> userCmsPathArrayList) {
		ConstModel.userCmsPathArrayList = userCmsPathArrayList;
	}
	public static TreeMap<String, ArrayList<CmsMenu>> getUserCmsMenuMap() {
		return userCmsMenuMap;
	}
	public static void setUserCmsMenuMap(TreeMap<String, ArrayList<CmsMenu>> userCmsMenuMap) {
		ConstModel.userCmsMenuMap = userCmsMenuMap;
	}
	
}
