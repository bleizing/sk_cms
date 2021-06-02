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
@Table(name = "donation_verifications")
public class DonationVerification extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column
	private boolean anonymous;
	
	@NotNull
	@Column
	private String content;
	
	// Berupa kumpulan url image yang dipisah oleh koma(,). Contoh "/images/test.jpg, /assets/coba.jpg"
	@Column
	private String images;
	
	// Untuk mengetahui benar atau salah campaign tersebut
	@NotNull
	@Column
	private boolean verified;
	
	// Untuk mengetahui apakah verifikasi sudah disetujui oleh admin cms
	@NotNull
	@Column
	private boolean approved = false;
	
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
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
