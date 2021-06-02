package com.infosys.sejuta_kebaikan_cms.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "campaigns")
public class Campaign extends BaseModel {
	@NotNull
	@Column
	private String title;
	
	@NotNull
	@Column
	private String description;
	
	@NotNull
	@Column
	private BigDecimal targetAmount;
	
	@NotNull
	@Column
	private Date startTime;
	
	@NotNull
	@Column
	private Date endTime;
	
	@NotNull
	@Column
	private String bannerImage;
	
	@NotNull
	@Column
	private Integer totalShare = 0;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "campaign_category_id")
	private CampaignCategory campaignCategory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cms_user_id")
	private CmsUser cmsUser;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public Integer getTotalShare() {
		return totalShare;
	}

	public void setTotalShare(Integer totalShare) {
		this.totalShare = totalShare;
	}

	public CampaignCategory getCampaignCategory() {
		return campaignCategory;
	}

	public void setCampaignCategory(CampaignCategory campaignCategory) {
		this.campaignCategory = campaignCategory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CmsUser getCmsUser() {
		return cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}
	
}
