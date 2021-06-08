package com.infosys.sejuta_kebaikan_cms.model.cms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.infosys.sejuta_kebaikan_cms.model.BaseModel;
import com.sun.istack.NotNull;

@Entity
@Table(name = "cms_group_menus")
public class CmsGroupMenu extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy = "cmsGroupMenu", fetch = FetchType.EAGER, 
			cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CmsMenu> cmsMenus = new ArrayList<CmsMenu>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CmsMenu> getCmsMenus() {
		return cmsMenus;
	}

	public void setCmsMenus(List<CmsMenu> cmsMenus) {
		this.cmsMenus = cmsMenus;
	}
	
}
