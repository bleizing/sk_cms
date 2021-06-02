package com.infosys.sejuta_kebaikan_cms.model.donation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.infosys.sejuta_kebaikan_cms.model.BaseModel;
import com.infosys.sejuta_kebaikan_cms.model.Campaign;
import com.infosys.sejuta_kebaikan_cms.model.Disbursement;
import com.infosys.sejuta_kebaikan_cms.model.User;
import com.sun.istack.NotNull;

@Entity
@Table(name = "donation_donates")
public class DonationDonate extends BaseModel {
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "disbursement_id")
	private Disbursement disbursement;

	// TODO : TBD

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

	public Disbursement getDisbursement() {
		return disbursement;
	}

	public void setDisbursement(Disbursement disbursement) {
		this.disbursement = disbursement;
	}
	
}
