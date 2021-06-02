package com.infosys.sejuta_kebaikan_cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "disbursements")
public class Disbursement extends BaseModel {
	@NotNull
	@Column
	private String invoiceNumber;
	
	// Status for disbursement process
	@NotNull
	@Column(length = 1)
	private Integer status;
	
	// TODO : TBD

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
