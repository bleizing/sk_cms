package com.infosys.sejuta_kebaikan_cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "payments")
public class Payment extends BaseModel {
	@NotNull
	@Column
	private String name;

	// TODO : TBD

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
