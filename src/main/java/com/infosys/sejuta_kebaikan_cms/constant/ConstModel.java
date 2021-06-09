package com.infosys.sejuta_kebaikan_cms.constant;

import java.util.ArrayList;
import java.util.TreeMap;

import com.infosys.sejuta_kebaikan_cms.model.cms.CmsMenu;

public class ConstModel {
	private static ArrayList<String> userCmsPathArrayList;
	private static TreeMap<String, ArrayList<CmsMenu>> userCmsMenuMap;

	public static ArrayList<String> getUserCmsPathArrayList() {
		return userCmsPathArrayList;
	}

	public static void userCmsPathArrayList(ArrayList<String> userCmsPathArrayList) {
		ConstModel.userCmsPathArrayList = userCmsPathArrayList;
	}

	public static TreeMap<String, ArrayList<CmsMenu>> getUserCmsMenuMap() {
		return userCmsMenuMap;
	}

	public static void setUserCmsMenuMap(TreeMap<String, ArrayList<CmsMenu>> userCmsMenuMap) {
		ConstModel.userCmsMenuMap = userCmsMenuMap;
	}
	
}
