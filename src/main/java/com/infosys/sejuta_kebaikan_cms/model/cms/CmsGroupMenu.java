package com.infosys.sejuta_kebaikan_cms.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
