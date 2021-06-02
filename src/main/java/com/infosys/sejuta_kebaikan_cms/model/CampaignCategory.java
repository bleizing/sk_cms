package com.infosys.sejuta_kebaikan_cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "campaign_categories")
public class CampaignCategory extends BaseModel {
	@NotNull
	@Column(length = 3, unique = true)
	private String code;
	
	@NotNull
	@Column(unique = true)
	private String name;
	
	@NotNull
	@Column
	private String logo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
