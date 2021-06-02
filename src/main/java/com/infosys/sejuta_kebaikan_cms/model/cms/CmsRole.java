package com.infosys.sejuta_kebaikan_cms.model.cms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.infosys.sejuta_kebaikan_cms.model.BaseModel;
import com.sun.istack.NotNull;

@Entity
@Table(name = "cms_roles")
public class CmsRole extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column
	private String name;
	
	@NotNull
	@Column
	private String category;
	
	@NotNull
	@Column
	private boolean master;
	
	@OneToMany(mappedBy = "cmsRole", fetch = FetchType.LAZY)
	private List<CmsUser> cmsUsers = new ArrayList<CmsUser>();

	@OneToMany(mappedBy = "cmsRole", fetch = FetchType.LAZY)
	private List<CmsRoleMenu> cmsRoleMenus = new ArrayList<CmsRoleMenu>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public List<CmsUser> getCmsUsers() {
		return cmsUsers;
	}

	public void setCmsUsers(List<CmsUser> cmsUsers) {
		this.cmsUsers = cmsUsers;
	}

	public List<CmsRoleMenu> getCmsRoleMenus() {
		return cmsRoleMenus;
	}

	public void setCmsRoleMenus(List<CmsRoleMenu> cmsRoleMenus) {
		this.cmsRoleMenus = cmsRoleMenus;
	}
	
}
