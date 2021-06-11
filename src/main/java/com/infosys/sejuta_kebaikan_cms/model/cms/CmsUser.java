package com.infosys.sejuta_kebaikan_cms.model.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.infosys.sejuta_kebaikan_cms.model.BaseModel;
import com.infosys.sejuta_kebaikan_cms.model.Merchant;
import com.sun.istack.NotNull;

@Entity
@Table(name = "cms_users")
public class CmsUser extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	@Column
	private String name;
	
	@NotNull
	@Column(unique = true)
	@Email
	private String email;
	
	@NotNull
	@Column(unique = true)
	private String phoneNumber;
	
	@NotNull
	@Column
	private String password;
	
	@Column
	private Date lastLoginAt;
	
	@Column
	private Date failedLoginAt;
	
	@Column
	private Integer failedLoginAttempt = 0;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cms_role_id")
	private CmsRole cmsRole;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public Date getFailedLoginAt() {
		return failedLoginAt;
	}

	public void setFailedLoginAt(Date failedLoginAt) {
		this.failedLoginAt = failedLoginAt;
	}

	public Integer getFailedLoginAttempt() {
		return failedLoginAttempt;
	}

	public void setFailedLoginAttempt(Integer failedLoginAttempt) {
		this.failedLoginAttempt = failedLoginAttempt;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public CmsRole getCmsRole() {
		return cmsRole;
	}

	public void setCmsRole(CmsRole cmsRole) {
		this.cmsRole = cmsRole;
	}

}
