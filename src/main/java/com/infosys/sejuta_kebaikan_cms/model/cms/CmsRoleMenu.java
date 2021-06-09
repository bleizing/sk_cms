package com.infosys.sejuta_kebaikan_cms.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.infosys.sejuta_kebaikan_cms.model.BaseModel;

@Entity
@Table(name = "cms_role_menus")
public class CmsRoleMenu extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "cms_role_id", referencedColumnName = "id")
	@ManyToOne
	private CmsRole cmsRole;

	@JoinColumn(name = "cms_menu_id", referencedColumnName = "id")
	@ManyToOne
	private CmsMenu cmsMenu;

	@Column
	private boolean needApproval;

	public CmsRole getCmsRole() {
		return cmsRole;
	}

	public void setCmsRole(CmsRole cmsRole) {
		this.cmsRole = cmsRole;
	}

	public CmsMenu getCmsMenu() {
		return cmsMenu;
	}

	public void setCmsMenu(CmsMenu cmsMenu) {
		this.cmsMenu = cmsMenu;
	}

	public boolean isNeedApproval() {
		return needApproval;
	}

	public void setNeedApproval(boolean needApproval) {
		this.needApproval = needApproval;
	}
	
}
