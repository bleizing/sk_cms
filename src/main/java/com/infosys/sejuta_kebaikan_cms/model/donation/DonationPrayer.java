package com.infosys.sejuta_kebaikan_cms.model.donation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.infosys.sejuta_kebaikan_cms.model.BaseModel;
import com.infosys.sejuta_kebaikan_cms.model.Campaign;
import com.infosys.sejuta_kebaikan_cms.model.User;
import com.sun.istack.NotNull;

@Entity
@Table(name = "donation_prayers")
public class DonationPrayer extends BaseModel {
	@NotNull
	@Column
	private boolean anonymous;
	
	@NotNull
	@Column
	private String content;
	
	@NotNull
	@Column
	private Integer totalShare = 0;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;
	
	// TODO : TBD

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTotalShare() {
		return totalShare;
	}

	public void setTotalShare(Integer totalShare) {
		this.totalShare = totalShare;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
}
