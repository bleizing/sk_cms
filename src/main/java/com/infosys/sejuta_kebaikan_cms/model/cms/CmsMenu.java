package com.infosys.sejuta_kebaikan_cms.model.cms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.infosys.sejuta_kebaikan_cms.model.BaseModel;
import com.sun.istack.NotNull;

@Entity
@Table(name = "cms_menus")
public class CmsMenu extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column
	private String name;
	
	@NotNull
	@Column
	private String url;
	
	@NotNull
	@Column(length = 4)
	private Integer level;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cms_group_menu_id")
	private CmsGroupMenu cmsGroupMenu;
	
	@OneToMany(mappedBy = "cmsMenu", fetch = FetchType.LAZY, 
			cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CmsRoleMenu> cmsRoleMenus = new ArrayList<CmsRoleMenu>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public CmsGroupMenu getCmsGroupMenu() {
		return cmsGroupMenu;
	}

	public void setCmsGroupMenu(CmsGroupMenu cmsGroupMenu) {
		this.cmsGroupMenu = cmsGroupMenu;
	}

	public List<CmsRoleMenu> getCmsRoleMenus() {
		return cmsRoleMenus;
	}

	public void setCmsRoleMenus(List<CmsRoleMenu> cmsRoleMenus) {
		this.cmsRoleMenus = cmsRoleMenus;
	}
	
}
