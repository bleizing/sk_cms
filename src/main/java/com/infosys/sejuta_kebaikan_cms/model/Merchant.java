package com.infosys.sejuta_kebaikan_cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "merchants")
public class Merchant extends BaseModel {
	@NotNull
	@Column(unique = true)
	private String name;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	@Column
	private String description;
	
	@NotNull
	@Column
	private String address;
	
	@NotNull
	@Column
	private String callCenter;
	
	@NotNull
	@Column
	private String logo;
	
	@NotNull
	@Column
	private String webUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCallCenter() {
		return callCenter;
	}

	public void setCallCenter(String callCenter) {
		this.callCenter = callCenter;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
}
