package com.infosys.sejuta_kebaikan_cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "users")
public class User extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column
	private String name;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@Column(length = 16, unique = true)
	private String phoneNumber;
	
	@Column
	private String password;
	
	@Column
	private String address;
	
	// 1. Manual Register, 2. GSignIn, 3. Facebook, 4. Intagram
	@NotNull
	@Column(length = 1)
	private Integer channel;
	
	@Column
	private String deviceId;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}
